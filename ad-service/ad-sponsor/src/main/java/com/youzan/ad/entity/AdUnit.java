package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author TCP
 * @create 2019/3/26 15:43
 * 推广单元表
 */
@Entity
@Table(name = "ad_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_status")
    private Integer unitStatus;

    @Column(name = "position_type")
    private Integer positionType;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public AdUnit(Long planId, String unitName, Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
