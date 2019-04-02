package com.youzan.ad.client;

import com.youzan.ad.client.vo.AdPlanGetRequest;
import com.youzan.ad.client.vo.AdPlanResponse;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/4/1 15:11
 */
@Component
public class SponsorClientHystrix {
    public CommonResponse<List<AdPlanResponse>> getPlan(AdPlanGetRequest adPlanGetRequest) {
        return new CommonResponse(-1, "网络异常", null);
    }
}
