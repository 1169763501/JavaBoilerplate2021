package com.nbnfsoft.admin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JsonUtils {

    /***
     * 对象转JSON字符串
     */
    public static <T> String toString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * JSON字符串转对象
     */
    public static <T> T fromString(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : new ObjectMapper().readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
