package com.neuedu.Interceptor;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class PortolAuthroityInterceptor implements HandlerInterceptor {
    @Autowired
    PortolAuthroityInterceptor portolAuthroityInterceptor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("======================普通用户拦截器");
        HttpSession session=request.getSession();
        UserInfo userInfo=(UserInfo) session.getAttribute("userInfo");
        if(userInfo==null){
            response.reset();
            try {
                //解决中文乱码
                response.setHeader("Content-Type","application/json;charset=UTF-8");
                PrintWriter printWriter=response.getWriter();
                ServerResponse serverResponse=ServerResponse.createServerResponseByFail("请先登录");
                String json= JsonUtils.objToString(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        if (userInfo.getRole()==0){
            response.reset();
            try {
                PrintWriter printWriter=response.getWriter();
                ServerResponse serverResponse=ServerResponse.createServerResponseByFail("权限不对");
                String json= JsonUtils.objToString(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
