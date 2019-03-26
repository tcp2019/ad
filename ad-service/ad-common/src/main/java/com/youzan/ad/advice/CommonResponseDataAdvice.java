package com.youzan.ad.advice;

import com.youzan.ad.annotation.IgnoreResponseAdvice;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对于正确信息的处理
 *
 * @Author TCP
 * @create 2019/3/26 11:00
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //如果当前类有@IgnoreResponseAdvice注解，则不支持增强通知
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //如果当前方法有@IgnoreResponseAdvice注解，则不支持增强通知
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse commonResponse = new CommonResponse(0, "");
        //如果当前传入对象为null
        if (null == o) {
            return commonResponse;
        }
        //如果当前传入对象属于CommonResponse 类型
        if (o instanceof CommonResponse) {
            commonResponse = (CommonResponse) o;
            return commonResponse;
        }
        //如果当前传入对象不属于CommonResponse类型
        commonResponse.setData(o);
        return commonResponse;
    }
}
