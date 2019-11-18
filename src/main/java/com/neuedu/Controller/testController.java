package com.neuedu.Controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class testController {

    @Autowired
    IUserInfoService iUserInfoService;

    @RequestMapping("/login")
    public UserInfo hello(UserInfo userInfo){
        UserInfo userInfo1 =iUserInfoService.findByUserNameAndPassword(userInfo.getUsername(),userInfo.getPassword());
        System.out.println("==========================================================================================");
        return userInfo1;
    }


    @RequestMapping("/res")
    public ServerResponse response(){
        return ServerResponse.createServerResponseBySucces(null,"hello world");
    }
}
