package com.neuedu.pojo;

import com.neuedu.viewObject.OrderViewObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageInfo {
    //当前页数
    private int currentPage;
    //总页数
    private int totalPage;
    //每页的条数
    private int number;
    //每页的商品详情
    private List<Product> products;

    //地址分页
    private List<Shipping> shippings;

    //订单分页
    public List<OrderViewObject> orderViewObjectList;

    public List<OrderViewObject> getOrderViewObjectList() {
        return orderViewObjectList;
    }

    public void setOrderViewObjectList(List<OrderViewObject> orderViewObjectList) {
        this.orderViewObjectList = orderViewObjectList;
    }



    public List<Shipping> getShippings() {
        return shippings;
    }



    public void setShippings(List<Shipping> shippings) {
        this.shippings = shippings;
    }



    public PageInfo(){}

    public PageInfo(int currentPage, int totalPage, int number, List<Product> products) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.number = number;
        this.products = products;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", number=" + number +
                ", products=" + products +
                ", shippings=" + shippings +
                '}';
    }
}
