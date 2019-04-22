package com.threewater.webserver.webtemplate.util;

import com.threewater.webserver.webtemplate.exception.CommonException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

public class HttpClientUtil {

    public static String httpSendPost(String url, String json){
        return httpSendPost(url, json, Charset.forName("utf-8"));
    }

    public static String httpSendPost(String url, String json, Charset charset){
        String result = "";
        try {
            // 创建httpclient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);

            // 设置参数到请求对象中
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            stringEntity.setContentEncoding(charset.toString());
            httpPost.setEntity(stringEntity);

            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            // 释放链接
            response.close();
        }catch (Exception e){
            throw new CommonException("TWFT0003", e, "POSTq请求发送失败");
        }

        return result;
    }

    public static String httpSendGet(String url){
        return httpSendGet(url, Charset.forName("utf-8"));
    }

    public static String httpSendGet(String url, Charset charset){
        String result = "";

        try {
            // 创建httpclient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建get方式请求对象
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-type", "application/json");
            // 通过请求对象获取响应对象
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            // 释放链接
            response.close();
        }catch(Exception e){
            throw new CommonException("TWFT0003", e, "GET请求发送失败");
        }

        return result;
    }

    public static void main(String[] args){
        String s = httpSendGet("http://www.baidu.com");
        System.out.println(s);
    }
}
