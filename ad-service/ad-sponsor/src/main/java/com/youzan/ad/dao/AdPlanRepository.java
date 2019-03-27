package com.youzan.ad.dao;

import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUser;
import com.youzan.ad.vo.AdPlanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/3/27 10:23
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {
    /**
     * 根据广告主查询广告计划
     *
     * @param userId
     * @return
     */
    List<AdPlan> findAllByUserId(Long userId);


    /**
     * 根据计划id集合和广告主id查新计划
     *
     * @param ids
     * @param userId
     * @return
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    /**
     * 根据状态查询所有计划
     *
     * @param planStatus
     * @return
     */
    List<AdPlan> findAllByPlanStatus(Integer planStatus);

    /**
     * 根据广告主和计划名称查询广告计划
     *
     * @param userId
     * @param userName
     * @return
     */
    AdPlan findByUserIdAndPlanName(Long userId, String userName);

    /**
     * 根据广告计划id和广告主id查询广告计划信息
     *
     * @param id
     * @param userId
     * @return
     */
    AdPlan findByIdAndUserId(Long id, Long userId);

}
