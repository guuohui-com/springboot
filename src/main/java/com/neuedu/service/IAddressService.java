package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import org.springframework.stereotype.Service;

@Service
public interface IAddressService {

    //springboot 添加收货地址
    public ServerResponse add(Integer userId, Shipping shipping);

    //springboot 删除某个收货地址
    public ServerResponse delete(Integer userId,Shipping shipping);

    //springboot 登录状态跟新某个收货地址
    public ServerResponse loginUpdateShipping(Shipping shipping,Integer userId);

    //springboot 查看地址详细信息
    public ServerResponse lookShippingMessage(Integer shippingId);

    //springboot 查看地址列表信息
    public ServerResponse lookShippingMessageList(Integer userId,Integer pageNum,Integer size);
}
