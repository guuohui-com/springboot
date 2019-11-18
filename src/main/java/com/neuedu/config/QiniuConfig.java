package com.neuedu.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.yml")
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {

    //上传凭证
    @Value("${qiniu.accessKey}")
    String accessKey ;
    @Value("${qiniu.secretKey}")
    String secretKey ;
    /*@Value("bucket")
    String bucket = ";*/

    //构建七牛云的配置类
    @Bean
    public com.qiniu.storage.Configuration configuration(){
        return new com.qiniu.storage.Configuration(Zone.zone1());
    }

    //上传管理器
    @Bean
    public UploadManager uploadManager(){
        return new UploadManager(configuration());
    }
    @Bean
    public Auth auth(){
        return  Auth.create(accessKey,secretKey);
    }

}
