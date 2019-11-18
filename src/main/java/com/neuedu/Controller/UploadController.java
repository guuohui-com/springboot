package com.neuedu.Controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @Autowired
    IuploadService iuploadService;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(@RequestParam("picfile") MultipartFile uploadFile){
        String newFileName=null;
        if(uploadFile!=null&&uploadFile.getOriginalFilename()!=null){
            String uuid= UUID.randomUUID().toString();
            String fileName=uploadFile.getOriginalFilename();
            String extendName=fileName.substring(fileName.lastIndexOf("."));
            newFileName=uuid+extendName;

            File file=new File("E:\\实训视频\\springMVC\\upload");
            if(!file.exists()){
                file.mkdir();
            }
            File newFile=new File(file,newFileName);
            try {
                //将新的文件写入磁盘
                uploadFile.transferTo(newFile);
                //上传到七牛云
                return iuploadService.uploadFile(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.createServerResponseByFail("上传七牛云失败");
    }

}
