package com.youzan.ad.advice;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用于发生异常的处理
 *
 * @author TCP
 *
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerException(AdException ad) {
        CommonResponse<String> commonResponse = new CommonResponse<>(-1, "业务繁忙");
        commonResponse.setData(ad.getMessage());
        return commonResponse;
    }
}
