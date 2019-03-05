package com.threewater.webserver.webtemplate.util;

import com.threewater.webserver.webtemplate.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求数据转换类
 */
public class HttpServletRequestProcess {

    private static final Logger log = LoggerFactory.getLogger(HttpServletRequestProcess.class);

    /**
     * 读取http请求并转换成json对象
     * @param request http请求
     * @return  json对象
     * @throws CommonException 读取失败/转换失败
     */
    public static Map readJsonObject(HttpServletRequest request){
        String jsonStr = readJsonString(request);
        try {
            return JsonUtil.str2obj(jsonStr, HashMap.class);
        } catch (Exception e) {
            throw new CommonException("TWFT0003",jsonStr + "数据格式不对,转换失败" + e.getMessage());
        }
    }

    private static String readJsonString(HttpServletRequest request){
        StringBuilder builder = new StringBuilder();
        String row;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), Charset.forName("UTF-8")));

            while ((row = reader.readLine()) != null){
                builder.append(row);
            }
        } catch (IOException e) {
            throw new CommonException("TWFT0003","流数据读取失败" + e.getMessage());
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("流关闭失败： " + e.getMessage());
                }
            }

        }
        return builder.toString();
    }
}
