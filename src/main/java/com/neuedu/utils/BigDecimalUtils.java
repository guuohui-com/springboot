package com.neuedu.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static BigDecimal add(double d1,double d2){
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1=new BigDecimal(String.valueOf(d2));
        return bigDecimal.add(bigDecimal1);
    }

    public static BigDecimal mul(double d1,double d2){
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1=new BigDecimal(String.valueOf(d2));
        return bigDecimal.multiply(bigDecimal1);
    }

    public static BigDecimal min(double d1,double d2){
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1=new BigDecimal(String.valueOf(d2));
        return bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP);
        //第二个参数，小数点后保留两位小数
        //第三个参数，四舍五入
    }
}
