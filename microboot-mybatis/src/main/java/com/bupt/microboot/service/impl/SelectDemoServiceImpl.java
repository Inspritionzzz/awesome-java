package com.bupt.microboot.service.impl;

import com.bupt.microboot.dao.UserDao;
import com.bupt.microboot.dto.UserInfoQueryParam;
import com.bupt.microboot.model.User;
import com.bupt.microboot.service.SelectDemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SelectDemoServiceImpl implements SelectDemoService {

    @Resource
    UserDao userDao;

    @Override
    public List<User> list(UserInfoQueryParam userInfoQueryParam) {
        return userDao.getList(userInfoQueryParam);
    }

    @Override
    public User getUser(String userId) {
        return userDao.getUser(userId);
    }


}
