package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author TCP
 * @create 2019/3/27 10:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private Long id;

    private String username;

    private String token;

    private Date createTime;

    private Date updateTime;
}
