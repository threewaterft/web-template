package com.threewater.webserver.webtemplate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * bean和map转换辅助类
 * @version 1.0
 */
public class BeanMapUtil {

    private  static  final Logger log = LoggerFactory.getLogger(BeanMapUtil.class);

    private  BeanMapUtil(){

    }

    /**
     * 将实体类转换成map
     * @param bean java对象
     * @param upperCase map的key是否大写
     * @param <T>
     * @return
     */
    public  static <T>  Map<String ,Object> bean2Map(T bean, boolean upperCase){
        if (bean == null)
            return Collections.emptyMap();
        Map<String ,Object> map = new HashMap<>();
        try {
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor p : props){
                String key = p.getName();
                if ("class".equals(key))
                    continue;
                Method getMethod = p.getReadMethod();
                //不包含同名get方法的属性被忽略
                if (getMethod == null)
                    continue;
                if (upperCase)
                    key = key.toUpperCase();
                map.put(key, getMethod.invoke(bean));
            }
            return  map;
        } catch (Exception e) {
            log.error("Bean to Map 转换出错：" + e.getMessage() );
        }

        return Collections.emptyMap();
    }

    /**
     * 将实体类转换成map
     * @param bean java对象
     * @param <T>
     * @return
     */
    public static <T>  Map<String ,Object> bean2Map(T bean){
       return  bean2Map(bean, false);
    }
}

