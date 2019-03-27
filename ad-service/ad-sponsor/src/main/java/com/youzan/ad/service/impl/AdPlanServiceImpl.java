package com.youzan.ad.service.impl;

import com.youzan.ad.constant.CommonStatus;
import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUserRepository;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUser;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.AdPlanService;
import com.youzan.ad.service.UserService;
import com.youzan.ad.utils.CommonUtils;
import com.youzan.ad.vo.AdPlanGetRequest;
import com.youzan.ad.vo.AdPlanRequest;
import com.youzan.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author TCP
 * @create 2019/3/27 11:48
 */
@Service
public class AdPlanServiceImpl implements AdPlanService {
    @Autowired
    private UserService userService;

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUserRepository adUserRepository;

    /**
     * 添加广告计划
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @Override
    public AdPlanResponse createPlan(AdPlanRequest adPlanRequest) throws AdException {
        //如果adPlanRequest信息不合法
        if (!adPlanRequest.createPlanValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        Optional<AdUser> byId = adUserRepository.findById(adPlanRequest.getUserId());
        //如果查不到广告主信息
        if (!byId.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_USER);
        }
        AdPlan byUserIdAndPlanName = adPlanRepository.findByUserIdAndPlanName(adPlanRequest.getUserId(), adPlanRequest.getPlanName());
        //判断当前添加的计划名称是否与数据库的计划名称重复
        if (byUserIdAndPlanName != null) {
            throw new AdException(Constants.ErrorMsg.PLAN_NAME_ALREADY_EXIST);
        }
        AdPlan adPlan = new AdPlan(adPlanRequest.getUserId(), adPlanRequest.getPlanName(), CommonUtils.parseStringDate(adPlanRequest.getStartTime()), CommonUtils.parseStringDate(adPlanRequest.getEndTime()));
        AdPlan adNewPlan = adPlanRepository.save(adPlan);
        return new AdPlanResponse(adNewPlan.getId(), adNewPlan.getPlanName());
    }

    /**
     * 修改广告计划
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @Override
    public AdPlanResponse updatePlan(AdPlanRequest adPlanRequest) throws AdException {
        //如果adPlanRequest信息不合法
        if (!adPlanRequest.updatePlanValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }

        //根据广告计划id和广告主id查询广告计划信息
        AdPlan adPlan = adPlanRepository.findByIdAndUserId(adPlanRequest.getId(), adPlanRequest.getUserId());

        //如果当前广告计划为空，则抛出异常：找不到这个广告计划
        if (null == adPlan) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        adPlan.setPlanName(adPlanRequest.getPlanName());
        adPlan.setStartDate(CommonUtils.parseStringDate(adPlanRequest.getStartTime()));
        adPlan.setUpdateDate(CommonUtils.parseStringDate(adPlanRequest.getEndTime()));
        return new AdPlanResponse(adPlan.getId(), adPlan.getPlanName());
    }

    /**
     * 查询广告主的广告计划
     *
     * @param adPlanGetRequest
     * @return
     * @throws AdException
     */
    @Override
    public List<AdPlan> getPlan(AdPlanGetRequest adPlanGetRequest) throws AdException {
        //校验参数
        if (!adPlanGetRequest.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        return adPlanRepository.findAllByIdInAndUserId(adPlanGetRequest.getIds(),
                adPlanGetRequest.getUserId());

    }

    /**
     * 删除广告计划
     *
     * @param adPlanRequest
     * @throws AdException
     */
    @Override
    public void deletePlan(AdPlanRequest adPlanRequest) throws AdException {
        //校验参数
        if (!adPlanRequest.deletePlanValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        //根据广告计划id和广告主id查询广告计划信息
        AdPlan adPlan = adPlanRepository.findByIdAndUserId(adPlanRequest.getId(), adPlanRequest.getUserId());

        //如果当前广告计划为空，则抛出异常：找不到这个广告计划
        if (null == adPlan) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        adPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adPlan.setUpdateDate(new Date());
        adPlanRepository.save(adPlan);
    }
}
