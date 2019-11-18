package com.neuedu.Controller;

import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@RequestMapping("user/")
public class UserController {
    @Autowired
    IUserInfoService iUserService;

    @RequestMapping(value = "/yanyan",method = RequestMethod.GET)
    public String yanyan(){
        return "yanyan";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String prelogin(){
        return "login.ftl";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@RequestParam("username") String username, @RequestParam("password")String password,
                        HttpServletRequest request, HttpServletResponse response, HttpSession session){
        int tem=iUserService.findByUserName(username);
        UserInfo userInfo=null;
        String mangerUaerNmae;
        String mangerPassword;
        String msg=null;
        String url=null;
        if(tem==0){
            msg="用户不存在";
            url="login";
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            return "error.ftl";
        }else{
             userInfo= iUserService.findByUserNameAndPassword(username,password);
             if(userInfo==null){
                 msg="=======密码错误==========";
                 url="login";
                 request.setAttribute("msg",msg);
                 request.setAttribute("url",url);
                 return "error.ftl";
             }else{
                 System.out.println("===========登录成功=============");
                 if(userInfo.getRole()==0){
                     //mangerUaerNmae= (String) session.getAttribute("username");
                     //mangerPassword= (String) session.getAttribute("password");
                     //if((mangerPassword==null)&&(mangerUaerNmae==null)){
                     System.out.println("===================登录成功"+username+"===================="+password);
                     //将用户名和密码放入cookie
                         Cookie username_cookie=new Cookie("username",username);
                         Cookie password_cookie=new Cookie("password",password);
                         username_cookie.setMaxAge(2000);
                         password_cookie.setMaxAge(2000);
                         response.addCookie(username_cookie);
                         response.addCookie(password_cookie);
                         //将用户名和密码放入session
                         session.setAttribute("username",username);
                         session.setAttribute("password",password);
                         request.setAttribute("userInfo",userInfo);
                        System.out.println("===================================home");
                     return "redirect:/againLogin";
                 }else{
                     return "login.ftl";
                 }
             }
        }
    }
    @RequestMapping("/againLogin")
    public String againLogin(){
        return "home.ftl";
    }

    @RequestMapping(value = "/commonuser")
    public  String searchCommonUser(HttpSession session){
        List<UserInfo> userInfoList=iUserService.findByRole(1);
        session.setAttribute("userInfoList",userInfoList);
        return "user/commonUser";
    }

    @RequestMapping(value = "/updateCommonUser/{id}",method = RequestMethod.GET)
    public String updateCommUser(@PathVariable("id") int id, HttpServletRequest request){
        UserInfo userInfo =iUserService.selectByPrimaryKey(id);
        System.out.println("============================="+userInfo);
        request.setAttribute("userInfo",userInfo);
        return "index.ftl";
    }

    @RequestMapping(value = "/updateCommonUser/{id}",method = RequestMethod.POST)
    public String updateCommUser(UserInfo userInfo, HttpServletRequest request){
        int rows=iUserService.updateByPrimaryKey(userInfo);
        if(rows>0){
            System.out.println("============================更新成功");
            return "redirect:/commonuser";
        }else{
            System.out.println("============================更新失败");
            String msg="更新失败";
            request.setAttribute("msg",msg);
            return "error.ftl";
        }
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    Cookie cookie1=new Cookie("username",null);
                    cookie1.setMaxAge(0);
                    response.addCookie(cookie1);
                }
                if (cookie.getName().equals("password")) {
                    Cookie cookie2=new Cookie("password",null);
                    cookie2.setMaxAge(0);
                    response.addCookie(cookie2);
                }
            }
        }
        return "redirect:/login";
    }

    public static void main(String[] args) {

    }

}
