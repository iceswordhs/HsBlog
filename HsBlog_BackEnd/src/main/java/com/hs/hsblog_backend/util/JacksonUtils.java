package com.hs.hsblog_backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Jackson工具类
 * @author Hs
 * @Date 2021/11/29 21:51
 */
public class JacksonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    // 将Object转换为字符串
    public static String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 将类转换为字符串
    public static String toJSONString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 从String中获取对应的类
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 从输入流中获取类
    public static <T> T readValue(InputStream src, Class<T> valueType) {
        try {
            return objectMapper.readValue(src, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将Object转换为给定的类
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
}
