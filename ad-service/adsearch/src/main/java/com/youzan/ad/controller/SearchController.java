package com.youzan.ad.controller;

import com.youzan.ad.client.SponsorClient;
import com.youzan.ad.client.vo.AdPlanGetRequest;
import com.youzan.ad.client.vo.AdPlanResponse;
import com.youzan.ad.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/4/1 13:26
 */
@RequestMapping("/search")
@RestController
public class SearchController {
    /*@Autowired
    private RestTemplate restTemplate;*/

    /* @PostMapping
     @IgnoreResponseAdvice
     public CommonResponse<List<AdPlanResponse>> findPLan(@RequestBody AdPlanGetRequest adPlanGetRequest) {
         return restTemplate.postForEntity("http://AD-SPONSOR/ad-sponsor/adPlan/getPlan", adPlanGetRequest, CommonResponse.class).getBody();

     }*/
    @Autowired
    private SponsorClient sponsorClient;

    @PostMapping
    public CommonResponse<List<AdPlanResponse>> findPlan(@RequestBody AdPlanGetRequest adPlanGetRequest) {
        return sponsorClient.findPlan(adPlanGetRequest);
    }
}
