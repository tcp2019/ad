package com.youzan.ad.dao;

import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author TCP
 * @create 2019/3/27 10:23
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    /**
     * 根据用户名查询用户记录
     *
     * @param username
     * @return
     */
    AdUser findByUsername(String username);



}
