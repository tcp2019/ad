package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一相应对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {
    /**
     * 业务状态码
     * 0：成功
     * -1：失败
     */
    private Integer code;
    /**
     * 业务的描述信息
     */
    private String message;

    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
