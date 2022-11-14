package com.bupt.microboot.service.impl;

import com.bupt.microboot.dao.UserDao;
import com.bupt.microboot.model.User;
import com.bupt.microboot.service.MybatisTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: delete update insert template
 * Created by jason on 2022/9/22 17:33
 */

@Service
public class MybatisTemplateServiceImpl implements MybatisTemplateService {

    @Resource
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void addUsers(List<User> userList){
        userDao.addUsers(userList);}

}
