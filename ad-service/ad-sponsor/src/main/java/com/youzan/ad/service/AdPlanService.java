package com.youzan.ad.service;

import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.*;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/3/27 10:45
 */
public interface AdPlanService {
    /**
     * 创建推广计划
     */
    AdPlanResponse createPlan(AdPlanRequest adPlanRequest) throws AdException;

    /**
     * 修改推广计划
     */
    AdPlanResponse updatePlan(AdPlanRequest adPlanRequest) throws AdException;

    /**
     * 查询推广计划
     */
    List<AdPlan> getPlan(AdPlanGetRequest adPlanGetRequest) throws AdException;

    /**
     * 删除推广计划
     */
    void deletePlan(AdPlanRequest adPlanRequest) throws AdException;

}
