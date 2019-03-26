package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author TCP
 * @create 2019/3/26 15:49
 *创意
 */
@Entity
@Table(name = "ad_creative")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdCreative {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer type;

    @Column(name = "material_type")
    private Integer materialType;

    private Integer height;

    private Integer width;

    private Integer duration;

    @Column(name = "audit_status")
    private Integer auditStatus;

    @Column(name = "user_id")
    private Long userId;

    private String url;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public AdCreative(String name, Integer type, Integer materialType, Integer height, Integer width, Integer duration, Integer auditStatus, Long userId, String url, Date createTime, Date updateTime) {
        this.name = name;
        this.type = type;
        this.materialType = materialType;
        this.height = height;
        this.width = width;
        this.duration = duration;
        this.auditStatus = CommonStatus.VALID.getStatus();
        this.userId = userId;
        this.url = url;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
