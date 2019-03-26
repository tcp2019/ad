package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author TCP
 * @create 2019/3/26 15:06
 */
@Entity
@Table(name = "ad_user")
@Data
@AllArgsConstructor
public class AdUser {
    @Id
    @Column(name = "id", nullable = false)
    /**
     * 主键生成策略
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "user_status", nullable = false)
    private Integer userStatus;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public AdUser() {
    }
}
