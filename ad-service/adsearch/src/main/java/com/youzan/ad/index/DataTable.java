package com.youzan.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author TCP
 * @create 2019/4/3 16:54
 */
@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {
    private static ApplicationContext applicationContext;
    private static Map<Class, Object> dataTableMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataTable.applicationContext = applicationContext;
    }

    //设置优先级
    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    /**
     * 根据bean的名称获取bean的对象
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T bean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据bean的类型获取bean的对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T bean(Class clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static <T> T of(Class<T> clazz) {
        T instance = (T) dataTableMap.get(clazz);
        if (instance != null) {
            return instance;
        }
        dataTableMap.put(clazz, bean(clazz));
        return (T) dataTableMap.get(clazz);
    }
}
