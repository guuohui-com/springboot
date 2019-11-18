package com.neuedu.Controller.SpringBoot;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/manger/product",method = RequestMethod.GET)
public class S_UploadController {
//
//    @Autowired
//    IProductService iProductService;
//    @RequestMapping("/upload")
//    public String upload(){
//        return "upload";
//    }
//    //springboot 图片上传接口
//    @ResponseBody
//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    public ServerResponse uploadServerSponse(@RequestParam(value = "uploadfile",required = false)MultipartFile file ){
//
//        String path="";
//        return iProductService.uploadServerSponse(file,path);
//    }
}
