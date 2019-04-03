package com.youzan.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author TCP
 * @create 2019/4/3 12:10
 * 推广单元兴趣限制
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitItTable {

    private Long unitId;

    private String itTag;
}
