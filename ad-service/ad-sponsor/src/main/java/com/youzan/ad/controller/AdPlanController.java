package com.youzan.ad.controller;

import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.AdPlanService;
import com.youzan.ad.vo.AdPlanGetRequest;
import com.youzan.ad.vo.AdPlanRequest;
import com.youzan.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/3/27 14:35
 */
@RestController
@RequestMapping("/adPlan")
public class AdPlanController {
    @Autowired
    private AdPlanService adPlanService;

    /**
     *
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @PostMapping
    public AdPlanResponse createPlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        return adPlanService.createPlan(adPlanRequest);
    }

    /**
     * 修改广告计划
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @PutMapping
    public AdPlanResponse updatePlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        return adPlanService.updatePlan(adPlanRequest);
    }

    /**
     * 查询广告计划
     *
     * @param adPlanGetRequest
     * @return
     * @throws AdException
     */
    @PostMapping(value = "/getPlan")
    public List<AdPlan> getPlan(@RequestBody AdPlanGetRequest adPlanGetRequest) throws AdException {
        return adPlanService.getPlan(adPlanGetRequest);
    }

    /**
     * 删除广告计划
     *
     * @param adPlanRequest
     * @throws AdException
     */
    @DeleteMapping
    public void deletePlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        adPlanService.deletePlan(adPlanRequest);
    }
}
