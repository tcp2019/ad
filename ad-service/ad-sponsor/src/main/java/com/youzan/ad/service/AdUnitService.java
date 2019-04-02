package com.youzan.ad.service;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.*;

/**
 * @Author TCP
 * @create 2019/3/29 10:33
 */
public interface AdUnitService {
    /**
     * 创建推广单元
     */
    AdUnitResponse createUnit(AdUnitRequest adUnitRequest) throws AdException;

    /**
     * 创建推广单元 关键词的限制
     *
     * @param adUnitKeywordRequest
     * @return
     * @throws AdException
     */
    AdUnitKeywordResponse createUnitKeyWord(AdUnitKeywordRequest adUnitKeywordRequest) throws AdException;

    /**
     * 创建单元 地域的限制
     *
     * @param adUnitItRequest
     * @return
     * @throws AdException
     */
    AdUnitItResponse createUnitIt(AdUnitItRequest adUnitItRequest) throws AdException;

    /**
     * 创建单元 兴趣的限制
     *
     * @param adUnitItRequest
     * @return
     * @throws AdException
     */
    AdCreativeUnitResponse createCreativeUnit(AdCreativeUnitRequest adUnitItRequest) throws AdException;
}
