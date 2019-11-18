package com.neuedu.service.impl;

import com.google.common.collect.Maps;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.PageInfo;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        //参数的非空校验
        if(shipping==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //添加
        shipping.setUserId(userId);
        shippingMapper.insert(shipping);
        //返回结果
        Map<String,Integer> map= Maps.newHashMap();
        map.put("shippingId",shipping.getId());
        return ServerResponse.createServerResponseBySucces("添加地址成功",map);
    }

    @Override
    public ServerResponse delete(Integer userId, Shipping shipping) {
        //参数的非空校验
        if(shipping==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //删除
        int rows=shippingMapper.deleteBuUserIdAndShipping(userId,shipping);
        if(rows==0){
            return ServerResponse.createServerResponseByFail("删除失败");
        }
        //返回结果
        return ServerResponse.createServerResponseBySucces("删除成功");
    }

    @Override
    public ServerResponse loginUpdateShipping(Shipping shipping,Integer userId) {
        //参数非空校验
        if(shipping==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //更新
        int rows =shippingMapper.updateNotEmptyByPrimaryKey(shipping,userId);
        //返回结果
        if(rows==0){
            return ServerResponse.createServerResponseByFail("更新失败");
        }
        return ServerResponse.createServerResponseBySucces("更新成功", shippingMapper.selectByPrimaryKey(shipping.getId()));
    }

    @Override
    public ServerResponse lookShippingMessage(Integer shippingId) {
        //参数非空校验
        if(shippingId==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //查询
        Shipping shipping=shippingMapper.selectByPrimaryKey(shippingId);
        if(shipping==null){
            return ServerResponse.createServerResponseByFail("无此地址");
        }
        return ServerResponse.createServerResponseBySucces("查询成功",shipping);
    }

    @Override
    public ServerResponse lookShippingMessageList(Integer userId,Integer pageNum, Integer size) {
        int start=(pageNum-1)*size;
        List<Shipping> shippingList= shippingMapper.searchAllShipping(userId,start,size);
        int totalnum=shippingMapper.selectAllShipping(userId);
        int totalPage=totalnum/size;
        if(totalnum%size!=0){
            totalPage++;
        }
        PageInfo pageInfo=new PageInfo();
        pageInfo.setShippings(shippingList);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setNumber(size);
        pageInfo.setTotalPage(totalPage);
        if(pageInfo==null){
            return ServerResponse.createServerResponseByFail("没有您的地址信息");
        }
        return  ServerResponse.createServerResponseBySucces("查询成功",pageInfo);
    }
}
