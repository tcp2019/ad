package com.youzan.ad.service.impl;

import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUnitItRepository;
import com.youzan.ad.dao.AdUnitRepository;
import com.youzan.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.entity.AdUnitIt;
import com.youzan.ad.entity.unit_condition.AdUnitKeyword;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.AdUnitService;
import com.youzan.ad.vo.*;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author TCP
 * @create 2019/3/29 10:33
 */
@Service
@Transactional
public class AdUnitServiceImpl implements AdUnitService {
    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUnitRepository adUnitRepository;

    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;

    @Autowired
    private AdUnitItRepository adUnitItRepository;

    /**
     * 根据推广计划和推广单元名称来创建推广单元
     * 并返回创建的推广单元的id和名称
     *
     * @param adUnitRequest
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitResponse createUnit(AdUnitRequest adUnitRequest) throws AdException {
        if (!adUnitRequest.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        Optional<AdPlan> byId = adPlanRepository.findById(adUnitRequest.getPlanId());
        if (!byId.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        //判断推广单元名称是否重复
        AdUnit adUnit = adUnitRepository.findByPlanIdAndUnitName(adUnitRequest.getPlanId(), adUnitRequest.getUnitName());
        if (adUnit != null) {
            throw new AdException(Constants.ErrorMsg.UNIT_NAME_ALREADY_EXIST);
        }
        AdUnit adUnit1 = adUnitRepository.save(
                new AdUnit(adUnitRequest.getPlanId(),
                        adUnitRequest.getUnitName(),
                        adUnitRequest.getPositionType(),
                        adUnitRequest.getBudget()));
        return new AdUnitResponse(adUnit1.getId(), adUnit1.getUnitName());
    }

    /**
     * 创建单元  兴趣的限制
     *
     * @param adUnitKeywordRequest
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitKeywordResponse createUnitKeyWord(AdUnitKeywordRequest adUnitKeywordRequest) throws AdException {
        //判断adUnitKeywordRequest是否为空
        if (adUnitKeywordRequest == null) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        //创建一个空的list
        List<Long> ids = Collections.emptyList();
        //获取adUnitKeywordRequest里面的所有的id进行判断，是否有对应的单元
        //jdk 1.8
        ids = adUnitKeywordRequest.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        List<AdUnit> allById = adUnitRepository.findAllById(ids);
        if (allById.size() != ids.size()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_UNIT);
        }
        //批量添加关键词
        List<AdUnitKeyword> unitKeywordList = Collections.emptyList();
        adUnitKeywordRequest.getUnitKeywords().forEach(
                i -> unitKeywordList.add(new AdUnitKeyword(i.getUnitId(), i.getUnitKeyword())));
        List<AdUnitKeyword> unitKeywordList1 = adUnitKeywordRepository.saveAll(unitKeywordList);
        //获得所有的关键词的id
        List<Long> unitKeyWordIds = unitKeywordList1.stream().map(
                AdUnitKeyword::getId
        ).collect(Collectors.toList());
        return new AdUnitKeywordResponse(unitKeyWordIds);
    }

    /**
     * 创建单元 地域的限制
     *
     * @param adUnitItRequest
     * @return
     * @throws AdException
     */
    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest adUnitItRequest) throws AdException {
        //判断adUnitKeywordRequest是否为空
        if (adUnitItRequest == null) {
            throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
        }
        //创建一个空的list
        List<Long> ids = Collections.emptyList();
        //获取adUnitKeywordRequest里面的所有的id进行判断，是否有对应的单元
        //jdk 1.8
        ids = adUnitItRequest.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        List<AdUnit> allById = adUnitRepository.findAllById(ids);
        //如果传入的id找不到对应的单元，则抛出异常
        if (allById.size() != ids.size()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_UNIT);
        }
        //批量添加关键词
        List<AdUnitIt> unitItList = Collections.emptyList();
        adUnitItRequest.getUnitIts().forEach(
                i -> unitItList.add(new AdUnitIt(i.getUnitId(), i.getItTag())));
        List<AdUnitIt> unitItList1 = adUnitItRepository.saveAll(unitItList);
        //获得所有的关键词的id
        List<Long> unitItIds = unitItList1.stream().map(
                AdUnitIt::getId
        ).collect(Collectors.toList());
        return new AdUnitItResponse(unitItIds);
    }

    @Override
    public AdCreativeUnitResponse createCreativeUnit(AdCreativeUnitRequest adUnitItRequest) throws AdException {
        return null;
    }
}
