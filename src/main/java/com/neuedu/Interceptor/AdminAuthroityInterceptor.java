package com.neuedu.Interceptor;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class AdminAuthroityInterceptor implements HandlerInterceptor{
    //请求到达Controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {

        /*request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("==========后台拦截器==================");
        HttpSession session= request.getSession();
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        //System.out.println("==========="+userInfo.getRole());
        if(userInfo==null){
            //未登录
            response.reset();
            try {
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
        if(userInfo.getRole()==1){
            response.reset();
            try {
                PrintWriter printWriter=response.getWriter();
                ServerResponse serverResponse=ServerResponse.createServerResponseByFail("没有管理员权限");
                String json= JsonUtils.objToString(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
