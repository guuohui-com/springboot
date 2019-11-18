package com.neuedu.service.impl;

import com.google.gson.Gson;
import com.neuedu.common.ServerResponse;
import com.neuedu.service.IuploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UploadServiceImpl implements IuploadService{

    @Autowired
    Auth auth;

    @Value("${qiniu.bucket}")
    private String bucketName;
    @Autowired
    UploadManager uploadManager;
    @Override
    public ServerResponse uploadFile(File uploadFile) {
        String uploadToken=auth.uploadToken(bucketName);
        System.out.println("============qiniuyun"+bucketName);
        try {
            Response response=uploadManager.put(uploadFile,null,uploadToken);

            //解析上传结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println("======================="+putRet.key);
            System.out.println("======================="+putRet.hash);
            return ServerResponse.createServerResponseBySucces(null,putRet.hash);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }
}
