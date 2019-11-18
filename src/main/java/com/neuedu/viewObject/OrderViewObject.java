package com.neuedu.viewObject;

import com.neuedu.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderViewObject {

    private Long orderNo;
    private BigDecimal payment;
    private Integer paymentType;
    private String paymentTypeDesc;
    private Integer postage;
    private Integer status;
    private String statusDesc;
    private String paymentTime;
    private String sendTime;
    private String endTime;
    private String closeTime;
    private String createTime;
    private List<OrderItemViewObject> orderItemVoList;
    private String imageHost;
    private Integer shippingId;
    private String receiverName;
    private ShippingViewObject shippingVo;

    public OrderViewObject() {
    }

    public OrderViewObject(Long orderNo, BigDecimal payment, Integer paymentType, String paymentTypeDesc, Integer postage, Integer status, String statusDesc, String paymentTime, String sendTime, String endTime, String closeTime, String createTime, List<OrderItemViewObject> orderItemVoList, String imageHost, Integer shippingId, String receiverName, ShippingViewObject shippingVo) {
        this.orderNo = orderNo;
        this.payment = payment;
        this.paymentType = paymentType;
        this.paymentTypeDesc = paymentTypeDesc;
        this.postage = postage;
        this.status = status;
        this.statusDesc = statusDesc;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.orderItemVoList = orderItemVoList;
        this.imageHost = imageHost;
        this.shippingId = shippingId;
        this.receiverName = receiverName;
        this.shippingVo = shippingVo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<OrderItemViewObject> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemViewObject> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ShippingViewObject getShippingVo() {
        return shippingVo;
    }

    public void setShippingVo(ShippingViewObject shippingVo) {
        this.shippingVo = shippingVo;
    }

    @Override
    public String toString() {
        return "OrderViewObject{" +
                "orderNo=" + orderNo +
                ", payment=" + payment +
                ", paymentType=" + paymentType +
                ", paymentTypeDesc='" + paymentTypeDesc + '\'' +
                ", postage=" + postage +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderItemVoList=" + orderItemVoList +
                ", imageHost='" + imageHost + '\'' +
                ", shippingId=" + shippingId +
                ", receiverName='" + receiverName + '\'' +
                ", shippingVo=" + shippingVo +
                '}';
    }
}
