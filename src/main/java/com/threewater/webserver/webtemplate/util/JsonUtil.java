package com.threewater.webserver.webtemplate.util;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.threewater.webserver.webtemplate.exception.CommonException;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T str2obj(String jsonStr, Class<T> clazz){
        try{
            return objectMapper.readValue(jsonStr, clazz);
        }catch (IOException e){
            throw new CommonException(e);
        }
    }

    public static String obj2str(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        }catch(IOException e){
            throw new CommonException(e);
        }
    }
}
