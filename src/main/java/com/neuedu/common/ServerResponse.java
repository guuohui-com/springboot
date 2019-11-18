package com.neuedu.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)// 非空的属性加入到json字符串当中，空的则不添加将对象中
public class ServerResponse<T> {
    /*服务端响应对象*/

    private int status;//调用接口的状态
    private  String msg;//调用接口的返回信息
    private T data;//向前端返回的数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServerResponse(){}
    private ServerResponse(int status){
        this.status=status;
    }
    private ServerResponse(int status,String msg){
        this.msg=msg;
        this.status=status;
    }
    private ServerResponse(int status,T data){
        this.data=data;
        this.status=status;
    }
    private ServerResponse(int status,String msg,T data){
        this.msg=msg;
        this.data=data;
        this.status=status;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status==0;
    }

    public  static<T>  ServerResponse<T> createServerResponseBySucces(){
        return new ServerResponse<>(0);
    }

    public  static<T>  ServerResponse<T> createServerResponseBySucces(T data){
        return new ServerResponse<>(0,data);
    }

    public  static<T>  ServerResponse<T> createServerResponseBySuccesas(String msg){
        return new ServerResponse<>(0,msg);
    }

    public  static<T>  ServerResponse<T> createServerResponseBySucces(String msg,T data){
        return new ServerResponse<>(0,msg,data);
    }


    public  static<T>  ServerResponse<T> createServerResponseByFail(String msg){
        return new ServerResponse<>(1,msg);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
