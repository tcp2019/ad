package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * 将前台请求参数放到AdUnitRequest里面，便于传参数
 *
 * @Author TCP
 * @create 2019/3/29 10:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitRequest {
    private Long planId;

    private String unitName;

    private Integer positionType;

    private Long budget;

    public boolean createValidate() {
        return null != planId && !StringUtils.isEmpty(unitName)
                && null != positionType && null != budget;
    }
}
