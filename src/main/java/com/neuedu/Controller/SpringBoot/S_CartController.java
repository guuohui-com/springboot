package com.neuedu.Controller.SpringBoot;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/cart")
@CrossOrigin
public class S_CartController {
    //springboot 购物车中添加商品

    @Autowired
    ICartService iCartService;

    @RequestMapping("addProductToCart")
    public ServerResponse addProductToCart(Integer productId, Integer count, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        System.out.println("==============================controller"+userInfo.getId());
        return iCartService.addProductToCart(userInfo.getId(),productId,count);
    }


    //springboot 查看购物车列表
    @RequestMapping("searchCartProductList")
    public ServerResponse searchCartProductList(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        System.out.println("=======searchCartProductList======"+userInfo);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.searchCartProductList(userInfo.getId());
    }

    //springboot 移除购物车中的某些商品
    @RequestMapping("deleteCartProductList")
    public ServerResponse deleteCartProductList(String productIds,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.deleteCartProductList(userInfo.getId(),productIds);
    }

    //springboot 选中购物车中的某个商品
    @RequestMapping("checkCartProduct")
    public ServerResponse checkCartProduct(Integer productId,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.checkCartProduct(userInfo.getId(),productId,1);
    }

    //springboot 取消选中购物车中的某个商品
    @RequestMapping("notCheckCartProduct")
    public ServerResponse notCheckCartProduct(Integer productId,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.checkCartProduct(userInfo.getId(),productId,0);
    }

    //springboot 全部选中购物车中的某个商品
    @RequestMapping("CheckCartAllProduct")
    public ServerResponse CheckCartAllProduct(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.checkCartProduct(userInfo.getId(),null,1);
    }

    //springboot 取消全部选中购物车中的某个商品
    @RequestMapping("notCheckCartAllProduct")
    public ServerResponse notCheckCartAllProduct(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.checkCartProduct(userInfo.getId(),null,0);
    }

    //查看购物车中被选中的商品
    @RequestMapping("/searchCartCheckedProduct")
    public ServerResponse searchCartCheckedProduct(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail("请您先进行登录");
        }
        return iCartService.searchCartCheckedProduct(userInfo.getId());
    }
}
