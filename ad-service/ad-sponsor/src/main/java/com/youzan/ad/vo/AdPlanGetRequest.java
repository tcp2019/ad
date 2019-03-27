package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/3/27 15:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanGetRequest {
    private List<Long> ids;
    private Long userId;

    /**
     * 参数验证
     *
     * @return
     */
    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
