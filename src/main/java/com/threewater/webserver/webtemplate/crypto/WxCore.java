package com.threewater.webserver.webtemplate.crypto;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

public class WxCore {
    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.fromObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws Exception{
        String appId = "wx748d2c3c8cc8f692";
        String encryptedData = "xs4XvZ4U4nAapSGFP6d77c+2UuaxVXj72Uw/PN8Flp4us1gs+g8CEzcSTF11mt4B/T9QZKaAq0zqVJZTbss87zGCBEmOZOw5ps/S6vzaXIVEDD1B5UHfBuHNCt7GZnbEKHuDxU2uJS9ExwP5epT8sgINWlYZlxdntMlflb8XgAuj5jpqtoHWAoXNa2hCV+XNqnNPMVVNxZigE9PFuj20urBvhsuzdk/V82zL7fFm44+2UTx3gXLZcPlrGjQq/wLaXC3//aQC16YXm71yTIiLj4j6lSyy41XuNH+DYNq5XwH1yF5lsqVfdv4WramsQCQ4NYzbdViQPrDfOHYD98f8Q6V5ggf2dBaXiQ+lrYTHZo5fWr1uX9gbGJnZAo7X91fWw/6sDE3euurx3mN3/GP35Ondz7vfRnTOvVWGxkaWpiMq2tikpF/1bLLz6ZHWe6Wr9MH8X/Azx/7qLqCrA4hJGQ==";
        String sessionKey = "gXffQU/Wfaz7KcsSyewV5A==";
        String iv = "0PdX0niNxeCKjpFIdbE2Gg==";
        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
    }
}
