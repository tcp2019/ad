package com.youzan.ad.constant;

import lombok.Getter;

/**
 * 定义状态枚举类型
 *
 * @Author TCP
 * @create 2019/3/26 15:25
 */
@Getter
public enum CommonStatus {
    VALID(1, "有效状态"),

    INVALID(0, "无效状态");

    Integer status;
    String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
