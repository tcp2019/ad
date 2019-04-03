package com.youzan.ad.index.interest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @Author TCP
 * @create 2019/4/3 12:10
 * 推广单元兴趣限制
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitItObject {

    private Long unitId;

    private String itTag;
}
