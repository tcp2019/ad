package com.youzan.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 用于作用在类和方法上，不处理异常的注解
 *
 * @Author TCP
 * @create 2019/3/26 11:20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreResponseAdvice {

}
