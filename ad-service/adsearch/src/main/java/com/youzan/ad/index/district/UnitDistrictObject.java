package com.youzan.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @Author TCP
 * @create 2019/4/3 13:47
 * 推广单元地域限制
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDistrictObject {

    private Long unitId;

    private String province;

    private String city;
}
