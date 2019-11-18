package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    //Springboot 想购物车中加入或更新商品
    public ServerResponse addProductToCart(Integer userId,Integer productId, Integer count);

    //Springboot 查看购物车中所有商品列表
    public ServerResponse searchCartProductList(Integer userId );

    //Springboot 删除购物车中某个商品
    public ServerResponse deleteCartProductList(Integer userId,String productIds);

    //选中某个商品
    public ServerResponse checkCartProduct(Integer userId,Integer productId,Integer check);

    //取消选中某个商品
    //public ServerResponse notCheckCartProduct(Integer userId, Integer productId);
    public ServerResponse searchCartCheckedProduct(Integer userId);
}
