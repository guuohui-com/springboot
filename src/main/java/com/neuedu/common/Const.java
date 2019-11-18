package com.neuedu.common;

public class Const {
    public static final String CURRENTUSER="current_user";

    public enum ResopnseCodeEnum{
        NEED_LOGIN(2,"需要登陆"),
        NO_PRIVILEGE(3,"无权限操作"),
        ;
        private int code;
        private String desc;
        private ResopnseCodeEnum(int code ,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum RoleEnum{

        ROLE_ADMINE(0,"管理员"),
        ROLE_CUSTONER(1,"普通用户");

        private int code;
        private String desc;
        private RoleEnum(int code ,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum PaymentEnum{

        ONLINE(1,"线上支付")
        ;
        private int code;
        private String desc;
        private PaymentEnum(int code ,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static PaymentEnum codeOf(Integer code){
            for(PaymentEnum paymentEnum:values()){
                if(code==paymentEnum.getCode());
                return paymentEnum;
            }
            return null;
        }
    }


    public enum PaymentPlatEnum{

        ALIPAY(1,"线上支付")
        ;
        private int code;
        private String desc;
        private PaymentPlatEnum(int code ,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static PaymentPlatEnum codeOf(Integer code){
            for(PaymentPlatEnum paymentPlatEnum:values()){
                if(code==paymentPlatEnum.getCode());
                return paymentPlatEnum;
            }
            return null;
        }
    }
}
