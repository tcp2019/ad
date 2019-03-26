package com.youzan.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author TCP
 * @create 2019/3/26 16:41
 * 兴趣限制
 */
@Table(name = "ad_unit_it")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitIt {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "it_tag")
    private String itTag;
}
