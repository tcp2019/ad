package com.youzan.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author TCP
 * @create 2019/4/2 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitTable {
    private Long unitId;

    private Integer unitStatus;

    private Integer positionType;

    private Long planId;

    /**
     * 更新推广单元索引
     *
     * @param newObject
     */
    public void update(AdUnitTable newObject) {
        if (null != newObject.getUnitId()) {
            this.unitId = newObject.getUnitId();
        }
        if (null != newObject.getPlanId()) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getPositionType()) {
            this.positionType = newObject.getPositionType();
        }
        if (null != newObject.getPositionType()) {
            this.unitStatus = newObject.getUnitStatus();
        }
    }
}
