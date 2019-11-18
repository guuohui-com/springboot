package com.neuedu.viewObject;

import java.math.BigDecimal;
import java.util.List;

public class CartViewObject {
    //购物信息集合
    private List<CartproductViewObject> cartproductViewObjectList;
    //是否全选
    private boolean isAllChecked;
    //总价格
    private BigDecimal cartTotalPrice;
    //购物车商品总数量
    private Integer totalNum;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }


    public CartViewObject(List<CartproductViewObject> cartproductViewObjectList, boolean isAllChecked, BigDecimal cartTotalPrice) {
        this.cartproductViewObjectList = cartproductViewObjectList;
        this.isAllChecked = isAllChecked;
        this.cartTotalPrice = cartTotalPrice;
    }

    @Override
    public String toString() {
        return "CartViewObject{" +
                "cartproductViewObjectList=" + cartproductViewObjectList +
                ", isAllChecked=" + isAllChecked +
                ", cartTotalPrice=" + cartTotalPrice +
                '}';
    }

    public CartViewObject() {}

    public List<CartproductViewObject> getCartproductViewObjectList() {
        return cartproductViewObjectList;
    }

    public void setCartproductViewObjectList(List<CartproductViewObject> cartproductViewObjectList) {
        this.cartproductViewObjectList = cartproductViewObjectList;
    }

    public boolean isAllChecked() {
        return isAllChecked;
    }

    public void setAllChecked(boolean allChecked) {
        isAllChecked = allChecked;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
}
