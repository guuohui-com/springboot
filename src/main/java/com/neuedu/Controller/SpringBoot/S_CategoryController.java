package com.neuedu.Controller.SpringBoot;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Hashtable;

@RestController
@RequestMapping("/manger/category")
@CrossOrigin
public class S_CategoryController {

    @Autowired
    ICategoryService iCategoryService;
    //获取子节点（平级）
    @RequestMapping("/getCategory.do")
    public ServerResponse getCategory(int categoryId, HttpSession session){
        //是否用户登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMINE.getCode()){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return iCategoryService.getCategoryByParentId(categoryId);
    }


    //增加节点
    @RequestMapping("/addCategory.do")
    public ServerResponse addCategory(@RequestParam(required = false,defaultValue = "0") int parentId,String categoryName, HttpSession session){
        //是否用户登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMINE.getCode()){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return iCategoryService.addCategoryByParentId(parentId,categoryName);
    }


    //修改节点
    @RequestMapping("/updateCategory.do")
    public ServerResponse updateCategory(Integer categoryId,String categoryName, HttpSession session){
        //是否用户登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMINE.getCode()){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return iCategoryService.updateCategory(categoryId,categoryName);
    }

    //获取当前分类Id和递归子节点CategoryId
    @RequestMapping("/getAllChildCategory.do")
    public ServerResponse getAllChildCategory(Integer categoryId, HttpSession session){
        //是否用户登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMINE.getCode()){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return iCategoryService.getAllChildCategory(categoryId);
    }
    @RequestMapping("/getRootCategory")
    public ServerResponse getRootCategory(HttpSession session){
        //是否用户登录
       /* UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NEED_LOGIN.getDesc());
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMINE.getCode()){
            return ServerResponse.createServerResponseByFail(Const.ResopnseCodeEnum.NO_PRIVILEGE.getDesc());
        }*/
        return iCategoryService.getRootCategory();
    }


}
