package com.youzan.ad.service;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.CreateUserRequest;
import com.youzan.ad.vo.CreateUserResponse;

/**
 * @Author TCP
 * @create 2019/3/27 10:45
 */
public interface UserService {
    /**
     * 创建广告主用户
     *
     * @param createUserRequest
     * @return
     */
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException;
}
