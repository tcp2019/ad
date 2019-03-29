package com.youzan.ad.service.impl;

import com.youzan.ad.constant.CommonStatus;
import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdUserRepository;
import com.youzan.ad.entity.AdUser;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.UserService;
import com.youzan.ad.utils.CommonUtils;
import com.youzan.ad.vo.CreateUserRequest;
import com.youzan.ad.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author TCP
 * @create 2019/3/27 10:51
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException {
        if (createUserRequest != null) {
            if (!createUserRequest.validate()) {
                throw new AdException(Constants.ErrorMsg.REQUEST_ILLEGAL_PARAMETERS);
            }

            AdUser adUser = adUserRepository.findByUsername(createUserRequest.getUsername());
            if (null != adUser) {
                throw new AdException(Constants.ErrorMsg.USER_NAME_ALREADY_EXIST);
            }
            AdUser adNewUser = adUserRepository.save(new AdUser(createUserRequest.getUsername(), CommonUtils.md5(createUserRequest.getToken())));
            return new CreateUserResponse(adNewUser.getId(), adNewUser.getUsername(), adNewUser.getToken(), adNewUser.getCreateTime(), adNewUser.getUpdateTime());
        }
        return null;
    }
}
