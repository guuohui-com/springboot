package com.neuedu.viewObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartproductViewObject implements Serializable{

    private Integer id;


    private Integer userId;


    private Integer productId;


    private Integer quantity;


    private String productName ;


    private String productSubtitle ;


    private String productMainImage ;


    private BigDecimal productPrice;


    private Integer productStatus ;


    private BigDecimal productTotalPrice;


    private Integer productStock;


    private Integer productChecked ;


    private String limitedQuantity;


    public CartproductViewObject(){}
    public CartproductViewObject(Integer id, Integer userId, Integer productId, Integer quantity, String productName, String productSubtitle, String productMainImage, BigDecimal productPrice, Integer productStatus, BigDecimal productTotalPrice, Integer productStock, Integer productChecked, String limitedQuantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
        this.productStock = productStock;
        this.productChecked = productChecked;
        this.limitedQuantity = limitedQuantity;
    }

    @Override
    public String toString() {
        return "CartproductViewObject{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", productSubtitle='" + productSubtitle + '\'' +
                ", productMainImage='" + productMainImage + '\'' +
                ", productPrice=" + productPrice +
                ", productStatus=" + productStatus +
                ", productTotalPrice=" + productTotalPrice +
                ", productStock=" + productStock +
                ", productChecked=" + productChecked +
                ", limitedQuantity='" + limitedQuantity + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }

    public String getLimitedQuantity() {
        return limitedQuantity;
    }

    public void setLimitedQuantity(String limitedQuantity) {
        this.limitedQuantity = limitedQuantity;
    }
}
