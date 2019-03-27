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
 * 推广计划
 */
@Entity
@Table(name = "ad_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_status")
    private Integer planStatus = CommonStatus.VALID.getStatus();

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public AdPlan(Long userId, String planName,
                  Date startDate, Date endDate) {
        this.userId = userId;
        this.planName = planName;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.createDate = new Date();
        this.updateDate = new Date();
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
