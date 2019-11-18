package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserInfoService;
import com.neuedu.utils.MD5Utils;
import com.neuedu.utils.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static com.neuedu.common.Const.CURRENTUSER;

@Service
public class UserServiceImpl implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int findByUserName(String username) {
        int rows=userInfoMapper.findByUserName(username);
        return rows;
    }

    @Override
    public UserInfo findByUserNameAndPassword(String username, String password) {
        //System.out.println("========serviceImpl======");
        UserInfo userInfo=userInfoMapper.findByUserNameAndPassword(username,password);
        //System.out.println("========serviceImpl======"+userInfo);
        return userInfo;
    }
    @Override
    public List<UserInfo> findByRole(int role){
        List<UserInfo> list =userInfoMapper.findByRole(role);
        return list;
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return  userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return  userInfoMapper.updateByPrimaryKey(record);
    }


    //登录接口
    @Override
    public ServerResponse login(String username,String password) {
        //参数的非空判断
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByFail("用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServerResponse.createServerResponseByFail("密码不能为空");
        }
        //检查用户名是否存在
        int rows = userInfoMapper.findByUserName(username);
        //System.out.println("============================="+rows);
        if (rows > 0) {
            //根据用户名和密码查找信息
            UserInfo userInfo = userInfoMapper.findByUserNameAndPassword(username, password/*MD5Utils.getMD5Code(password)*/);
            if (userInfo==null){
                return ServerResponse.createServerResponseByFail("密码错误");
            }
            //userInfo.setPassword("");
            return ServerResponse.createServerResponseBySucces(userInfo);
        } else {
            return ServerResponse.createServerResponseByFail("用户名不存在");
        }
    }

    //注册接口
    @Override
    public ServerResponse register(UserInfo userInfo) {

        //参数非空校验
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("参数必须填写");
        }
        //校验用户名
        int rows = userInfoMapper.findByUserName(userInfo.getUsername());
        if(rows>0){
            return ServerResponse.createServerResponseByFail("用户名已存在");
        }
        //校验邮箱
        int emailRows=userInfoMapper.findByEmail(userInfo.getEmail());
        if(emailRows>0){
            return ServerResponse.createServerResponseByFail("邮箱已存在");
        }
        //注册
        userInfo.setRole(0);
        //userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        int insertNum =userInfoMapper.insert(userInfo);
        if(insertNum>0){
            return ServerResponse.createServerResponseBySucces(null,"注册成功");
        }else{
            return  ServerResponse.createServerResponseByFail("注册失败");
        }
    }

    //根据用户名找回密保问题接口
    @Override
    public ServerResponse forgetPasswordGetQuestion(String username) {
        //参数校验
        if(username==null||username.equals("")){
            return  ServerResponse.createServerResponseByFail("用户名为空");
        }
        //校验用户名
        int row=userInfoMapper.findByUserName(username);
        if(row==0){
            return ServerResponse.createServerResponseByFail("用户不存在");
        }
        //查找密保问题
        String question=userInfoMapper.findQuestionByUaername(username);
        return ServerResponse.createServerResponseBySucces(null,question);
    }

    //校验用户提交答案接口
    @Override
    public ServerResponse forgetPasswordCheckAnswer(String username, String question, String answer) {
        //参数校验
        if(username==null||username.equals("")){
            return  ServerResponse.createServerResponseByFail("用户名为空");
        }
        if(question==null||question.equals("")){
            return  ServerResponse.createServerResponseByFail("问题名为空");
        }
        if(answer==null||answer.equals("")){
            return  ServerResponse.createServerResponseByFail("答案为空");
        }
        //根据username question answer查询
        int rows=userInfoMapper.forgetPasswordCheckAnswer(username,question,answer);
        if(rows==0){
            return ServerResponse.createServerResponseByFail("答案错误");
        }
        //服务端生成一个token，并且返回给客户端
        String forgetToken= UUID.randomUUID().toString();
        //guavaCache
        TokenCache.set(username,forgetToken);
        return ServerResponse.createServerResponseBySucces(forgetToken);
    }

    //springboot 忘记密码的重置密码
    @Override
    public ServerResponse forgetPasswordSetPassword(String username, String passwordNew, String forgetToken) {
        //参数校验
        if(username==null||username.equals("")){
            return  ServerResponse.createServerResponseByFail("用户名为空");
        }
        if(passwordNew==null||passwordNew.equals("")){
            return  ServerResponse.createServerResponseByFail("新密码不能为空");
        }
        if(forgetToken==null||forgetToken.equals("")){
            return  ServerResponse.createServerResponseByFail("forgetToken为空");
        }
        //token校验
        String token = TokenCache.get(username);
        if(token==null){
            return ServerResponse.createServerResponseByFail("token过期");
        }
        if(!token.equals(forgetToken)){
            return ServerResponse.createServerResponseByFail("无效的token");
        }
        //修改密码
        int rows=userInfoMapper.updatePassword(username,passwordNew);
        if(rows>0){
            return ServerResponse.createServerResponseBySucces("修改成功",username);
        }
        return ServerResponse.createServerResponseByFail("密码修改失败");
    }

    @Override
    public ServerResponse getLoginUser(HttpSession session) {
        session.getAttribute(Const.CURRENTUSER);
        System.out.println("=====================session"+session.getAttribute(Const.CURRENTUSER));
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null||userInfo.equals(null)){
            return ServerResponse.createServerResponseByFail("用户尚未登录");
        }
        return ServerResponse.createServerResponseBySucces(null,userInfo);
    }

    @Override
    public ServerResponse loginUpdatePassword(String passwordNew,HttpSession session) {
        session.getAttribute(Const.CURRENTUSER);
        //System.out.println("=====================session"+session.getAttribute(Const.CURRENTUSER));
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null||userInfo.equals(null)){
            return ServerResponse.createServerResponseByFail("用户尚未登录");
        }
        String username=userInfo.getUsername();
        int rows=userInfoMapper.updatePassword(username,passwordNew);
        if(rows>0){
            //删除session
            userInfo.setPassword(passwordNew);
            session.setAttribute(Const.CURRENTUSER,userInfo);
            return ServerResponse.createServerResponseBySucces("修改成功");
        }
        return ServerResponse.createServerResponseByFail("修改失败");
    }

    //springboot 登陆中修改个人信息
    @Override
    public ServerResponse loginUpdateUserInfo(UserInfo userInfo, HttpSession session) {
        session.getAttribute(Const.CURRENTUSER);
        UserInfo userInfoOld= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null||userInfo.equals(null)){
            return ServerResponse.createServerResponseByFail("用户尚未登录");
        }
        int newId=userInfoOld.getId();
        userInfo.setId(newId);
        int rows=userInfoMapper.updateByPrimaryKey(userInfo);
        if(rows>0){
            session.setAttribute(Const.CURRENTUSER,userInfo);
            return ServerResponse.createServerResponseBySucces(null,userInfo);
        }
        return ServerResponse.createServerResponseByFail("修改失败");
    }
}
