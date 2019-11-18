package com.neuedu.utils;


import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;

//JsonY与java对象相互转换
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象中所有字段序列化
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        //取消date默认格式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);

        //忽略空的bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);

        //设置所有的日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.STANDARD_FORMAT));

        //忽略在json字符串中存在，在java中不存在的属性，防止出错
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    //对象转字符串
    public  static  <T> String objToString(T obj){
        if(obj==null){
            return null;
        }
        try {
            return obj instanceof String?(String) obj:objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //对象转字符串------------进行了格式化
    public  static  <T> String objToStringPreety(T obj){
        if(obj==null){
            return null;
        }
        try {
            return obj instanceof String?(String) obj:objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //字符串转对象
    public  static  <T> T stringToObj(String str,Class<T> clazz){

        if(StringUtils.isEmpty(str)||clazz==null){
            return null;
        }
        try {
            return clazz.equals(String.class)?(T)str: objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //json数组转集合
    public static <T>T stringToJson(String str, TypeReference<T> tTypeReference){
        if(StringUtils.isEmpty(str)||tTypeReference==null){
            return null;
        }
        try {
            return tTypeReference.getType().equals(String.class)?(T)str:
                    objectMapper.readValue(str,tTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static <T> T stringToObj(String str,Class<?> collectionClass,Class<?>...elements){
        JavaType javaType=objectMapper.getTypeFactory().constructParametricType(collectionClass,elements);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
