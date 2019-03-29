package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 将前台请求参数放到AdUnitKeywordRequest里面，便于传参数
 *
 * @Author TCP
 * @create 2019/3/29 13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeywordRequest {
    /**
     * 存储UnitKeywords对象
     */
    List<UnitKeyword> unitKeywords;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitKeyword {
        Long unitId;
        String unitKeyword;
    }
}
