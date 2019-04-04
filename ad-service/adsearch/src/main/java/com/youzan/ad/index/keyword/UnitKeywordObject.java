package com.youzan.ad.index.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author TCP
 * @create 2019/4/2 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitKeywordObject {
    private String keyword;
    private Long unitId;
}
