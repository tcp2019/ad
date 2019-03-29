package com.youzan.ad.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author TCP
 * @create 2019/3/26 16:41
 */
@Table(name = "ad_unit-district")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitDistrict {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    private String province;

    private String city;
}
