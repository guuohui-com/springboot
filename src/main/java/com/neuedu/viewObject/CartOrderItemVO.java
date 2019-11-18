package com.neuedu.viewObject;


import java.math.BigDecimal;
import java.util.List;

public class CartOrderItemVO {
    private List<OrderItemViewObject> orderItemViewObjectList;
    private String imageHost;
    private BigDecimal totalPrice;

    public List<OrderItemViewObject> getOrderItemViewObjectList() {
        return orderItemViewObjectList;
    }

    public void setOrderItemViewObjectList(List<OrderItemViewObject> orderItemViewObjectList) {
        this.orderItemViewObjectList = orderItemViewObjectList;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
