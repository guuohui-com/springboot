package com.neuedu.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//基于guava 缓存类
public class TokenCache {
    private static final LoadingCache<String,String> loadingCache=
            CacheBuilder
                    .newBuilder()
                    .initialCapacity(1000)//出事缓存项
                    .maximumSize(10000)//缓存项最大值
                    .expireAfterAccess(10, TimeUnit.MINUTES)
                    .build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return "null";
        }
    });

    public static void set(String key,String value){
        loadingCache.put(key,value);
    }
    public static String get(String key){
        String result = null;
        try {
            result = loadingCache.get(key);
            if(!result.equals("null")){
                return result;
            }
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

