package com.neuedu.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {


    public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";
    //Data转化string
    public static String dateToString(Date date,String formate){
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(formate);
    }
    //Data转化string
    public static String dateToString(Date date){
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    //将字符串转Date
    public static Date stringToDate(String date,String formate){
        DateTimeFormatter dateTimeFormat=DateTimeFormat.forPattern(formate);
        DateTime dateTime =dateTimeFormat.parseDateTime(date);
        return dateTime.toDate();
    }
    public static Date stringToDate(String date){
        DateTimeFormatter dateTimeFormat=DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime =dateTimeFormat.parseDateTime(date);
        return dateTime.toDate();
    }

}
