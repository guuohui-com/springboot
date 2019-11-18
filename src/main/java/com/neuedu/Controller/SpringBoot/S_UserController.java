package com.neuedu.Controller.SpringBoot;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserInfoService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class S_UserController {
    @Autowired
    IUserInfoService iUserInfoService;

    //springboot登录接口
    @RequestMapping(value = "/login.do")
    public ServerResponse login(String username,String password,HttpSession session){
        ServerResponse serverResponse=iUserInfoService.login(username,password);
        if(serverResponse.isSuccess()){
            UserInfo userInfo=(UserInfo) serverResponse.getData();
            //登陆成功之后,放入session
            session.setAttribute(Const.CURRENTUSER,userInfo);
            System.out.println("===========userInfo========="+session.getAttribute(Const.CURRENTUSER));
        }
        return serverResponse;
    }


    //springboot注册接口
    @RequestMapping("/register.do")
    public ServerResponse register(UserInfo userInfo,HttpSession session){
        ServerResponse serverResponse=iUserInfoService.register(userInfo);
        return serverResponse;
    }

    //springboot忘记密码查密保问题
    @RequestMapping("/forgetPasswordGetQuestion.do")
    public ServerResponse forgetPasswordGetQuestion(String username){
        System.out.println("==================================="+username);
        ServerResponse serverResponse=iUserInfoService.forgetPasswordGetQuestion(username);
        return serverResponse;
    }

    //springboot提交问题答案
    @RequestMapping("/forgetPasswordCheckAnswer.do")
    public  ServerResponse  forgetPasswordCheckAnswer(String username, String question,String answer){
        ServerResponse serverResponse=iUserInfoService.forgetPasswordCheckAnswer(username,question,answer);
        return serverResponse;
    }

    //springboot 验证密保问题之后重置密码
    @RequestMapping("/forgetPasswordSetPassword.do")
    public  ServerResponse  forgetPasswordSetPassword(String username, String passwordNew,String forgetToken){


        ServerResponse serverResponse=iUserInfoService.forgetPasswordSetPassword(username,passwordNew,forgetToken);
        return serverResponse;
    }

    //获取登录用户信息
    @RequestMapping("/getLoginUser")
    public  ServerResponse getLoginUser(HttpSession session){
        ServerResponse serverResponse=iUserInfoService.getLoginUser(session);
        return serverResponse;
    }

    //登录中重置密码
    @RequestMapping("loginUpdatePassword")
    public ServerResponse loginUpdatePassword(String passwordNew,HttpSession session){
        ServerResponse serverResponse= iUserInfoService.loginUpdatePassword(passwordNew,session);
        return serverResponse;
    }

    //登陆中修改个人信息
    @RequestMapping("loginUpdateUserInfo")
    public ServerResponse loginUpdateUserInfo(UserInfo userInfo,HttpSession session){
        ServerResponse serverResponse= iUserInfoService.loginUpdateUserInfo(userInfo,session);
        return serverResponse;
    }


    //退出登录
    @RequestMapping("loginOut")
    public ServerResponse loginOut(HttpSession session){
        session.removeAttribute(Const.CURRENTUSER);
        return ServerResponse.createServerResponseBySucces("退出成功");
    }


}
