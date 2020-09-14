package com.lead.utils;

import com.alibaba.fastjson.JSON;
import com.lead.execption.CheckException;
import com.sun.tools.javac.util.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 登陆用户的字段校验
 *
 * @author mac
 * @date 2020/9/13
 */
public class VerifyEntity {

    private static Logger log = LoggerFactory.getLogger(VerifyEntity.class);

    public static void verify(Object object) throws IllegalAccessException {
        ArrayList<String> strings = new ArrayList<>();
        //反射获取类上的所有字段
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotEmpty.class)) {
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                //获取注解上的value值
                String message = annotation.message();
                if ("name".equals(field.getName())) {
                    if (StringUtils.isEmpty(field.get(object))) {
                        strings.add(String.format("%s", message));
                    }
                }
                if ("password".equals(field.getName())) {
                    if (StringUtils.isEmpty(field.get(object))) {
                        strings.add(String.format("%s", message));
                    }
                }
            }
        }
        if (!ObjectUtils.isEmpty(strings)) {
            log.info("此次校验前台传入的用户和密码至少有一样为空");
            throw new CheckException(JSON.toJSONString(strings));
        }

    }
}
