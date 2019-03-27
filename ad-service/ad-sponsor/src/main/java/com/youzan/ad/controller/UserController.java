package com.youzan.ad.controller;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.UserService;
import com.youzan.ad.vo.CreateUserRequest;
import com.youzan.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TCP
 * @create 2019/3/27 11:23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 创建一个对象
     *
     * @param createUserRequest
     * @return
     * @throws AdException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CreateUserResponse save(@RequestBody CreateUserRequest createUserRequest) throws AdException {
        log.info("ad-sponsor:saveUser->{}",
                JSON.toJSONString(createUserRequest));
        return userService.createUser(createUserRequest);
    }
}
