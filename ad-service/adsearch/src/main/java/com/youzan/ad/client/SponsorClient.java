package com.youzan.ad.client;

import com.youzan.ad.client.vo.AdPlanGetRequest;
import com.youzan.ad.client.vo.AdPlanResponse;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/4/1 14:54
 */
@FeignClient(value = "AD-SPONSOR", fallback = SponsorClientHystrix.class)
public interface SponsorClient {
    /**
     * 获取ad-sponsor服务中AdPlanController里面的getPlan方法
     *
     * @param adPlanGetRequest
     * @return
     */
    @RequestMapping(value = "/ad-sponsor/adPlan/getPlan", method = RequestMethod.POST)
    public CommonResponse<List<AdPlanResponse>> findPlan(@RequestBody AdPlanGetRequest adPlanGetRequest);
}
