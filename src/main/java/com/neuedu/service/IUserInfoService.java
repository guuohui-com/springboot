package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IUserInfoService {
    public int findByUserName(String username);
    public UserInfo findByUserNameAndPassword(@Param("usernaem") String username, @Param("password") String password);
    public List<UserInfo> findByRole(int role);
    public UserInfo selectByPrimaryKey(Integer id);
    public int updateByPrimaryKey(UserInfo record);


    //springboot 登录
    public ServerResponse login(String username, String password);
    //springboot 注册
    public ServerResponse register(UserInfo userInfo);
    //springboot 根据用户名查密保问题
    public ServerResponse forgetPasswordGetQuestion(String username);
    //springboot 校验问题答案
    public  ServerResponse forgetPasswordCheckAnswer(String userame,String question,String answer);
    //springboot 校验问题成功之后重置密码
    public  ServerResponse forgetPasswordSetPassword(String userame,String question,String forgetToken);
    //springboot 获取登录用户信息
    public  ServerResponse getLoginUser(HttpSession session);
    //springboot 登录中重置密码
    public  ServerResponse loginUpdatePassword(String passwordNew,HttpSession session);
    //springboot 登陆中修改个人信息
    public ServerResponse loginUpdateUserInfo(UserInfo userInfo,HttpSession session);

}
