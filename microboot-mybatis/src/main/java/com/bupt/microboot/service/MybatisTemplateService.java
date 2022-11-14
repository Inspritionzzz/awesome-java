package com.bupt.microboot.service;

import com.bupt.microboot.model.User;

import java.util.List;

/**
 * @Description: mybatis xml template
 * Created by jason on 2022/9/22 13:28
 */

public interface MybatisTemplateService {
    void addUser(User user);

    void addUsers(List<User> userList);
}
