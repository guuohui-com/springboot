package com.neuedu;

import com.google.common.collect.Lists;
import com.neuedu.Interceptor.AdminAuthroityInterceptor;
import com.neuedu.Interceptor.PortolAuthroityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootConfiguration
public class MySpringBootConfig implements WebMvcConfigurer{

    //拦截后台请求，验证用户是否等录
    @Autowired
    AdminAuthroityInterceptor adminAuthroityInterceptor;
    @Autowired
    PortolAuthroityInterceptor portolAuthroityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加后台拦截路径
        registry.addInterceptor(adminAuthroityInterceptor).addPathPatterns("/manger/**");

        //添加前台拦截路径
        List<String> stringList= Lists.newArrayList();
        stringList.add("/user/**");
        stringList.add("/cart/**");
        stringList.add("/portal/order/**");
        stringList.add("/shipping/**");

        //需要排除的路径
        List<String> excludePath=Lists.newArrayList();
        excludePath.add("/user/register.do");
        excludePath.add("/user/login.do");
        excludePath.add("/user/forgetPasswordGetQuestion.do");
        excludePath.add("/user/forgetPasswordCheckAnswer.do");
        excludePath.add("/user/forgetPasswordSetPassword.do");

        excludePath.add("/portal/order/alipayCallBack");
        registry.addInterceptor(adminAuthroityInterceptor).addPathPatterns(stringList).excludePathPatterns(excludePath);
    }
}
