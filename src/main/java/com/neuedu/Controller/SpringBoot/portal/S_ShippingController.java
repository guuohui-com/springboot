package com.neuedu.Controller.SpringBoot.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/shipping")
@CrossOrigin
public class S_ShippingController {
    @Autowired
    IAddressService iAddressService;
    //添加地址
    @RequestMapping("addShipping")
    public ServerResponse addShipping(HttpSession session, Shipping shipping){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("需要登陆");
        }
        return iAddressService.add(userInfo.getId(),shipping);
    }


    //删除地址
    @RequestMapping("deleteShipping")
    public ServerResponse deleteShipping(HttpSession session, Shipping shipping){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("需要登陆");
        }
        return iAddressService.delete(userInfo.getId(),shipping);
    }


    //登录状态修改地址地址
    @RequestMapping("loginUpdateShipping")
    public ServerResponse loginUpdateShipping(HttpSession session, Shipping shipping){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("需要登陆");
        }
        shipping.setUserId(userInfo.getId());
        return iAddressService.loginUpdateShipping(shipping,userInfo.getId());

    }

    //查看具体地址信息
    @RequestMapping("lookShippingMessage")
    public ServerResponse lookShippingMessage(HttpSession session, Integer shippingId){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("需要登陆");
        }
        return iAddressService.lookShippingMessage(shippingId);
    }

    //查看所有地址列表信息
    @RequestMapping("lookShippingMessageList")
    public ServerResponse lookShippingMessageList(HttpSession session, Integer shippingId,
                                                  @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "size",required = false,defaultValue = "10")Integer size){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("需要登陆");
        }
        return iAddressService.lookShippingMessageList(userInfo.getId(),pageNum,size);
    }
}
