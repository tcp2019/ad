package com.youzan.ad.dao;

import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/3/27 10:23
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    /**
     * 根据计划id和单元名称查询推广单元
     *
     * @param planId
     * @param unitName
     * @return
     */
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    /**
     * 根据状态查询推广单元
     *
     * @param status
     * @return
     */
    List<AdUnit> findAllByUnitStatus(Integer status);
}
