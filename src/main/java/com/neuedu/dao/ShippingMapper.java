package com.neuedu.dao;

import com.neuedu.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbggenerated
     */
    int insert(Shipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbggenerated
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbggenerated
     */
    List<Shipping> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Shipping record);


    //根据userId和shipping删除
    int deleteBuUserIdAndShipping(@Param("userId") Integer userId, @Param("shipping") Shipping shipping);

    //将不等于空的参数进行跟新
    int updateNotEmptyByPrimaryKey(@Param("shipping")Shipping record,@Param("userId") Integer userId);

    //分页查看所有shipping信息
    List<Shipping> searchAllShipping(@Param("userId") Integer userId,@Param("start") Integer start,@Param("size") Integer size);

    //查看某个用户有几条地址信息
    int selectAllShipping(@Param("userId") Integer userId);
}