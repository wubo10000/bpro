package com.bms.system.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zhaoyi
 * @version : 1.0.0
 * @date : 2016/3/17 15:36
 * @description :
 */
public class BeanUtils
{
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException
    {
        Class type = bean.getClass();
        Map returnMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors)
        {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class"))
            {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null)
                {
                    returnMap.put(propertyName, result);
                }
                else
                {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 获取bean的属性名集合
     * @param clazz bean
     * @return List<String>
     */
    public static List<String> getBeanNames(Class clazz)
    {
        List<String> result = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0)
        {
            for (Field item : fields)
            {
                result.add(item.getName());
            }
        }

        return result;
    }
}
