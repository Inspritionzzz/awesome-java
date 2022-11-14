package com.bupt.microboot.service;

import com.bupt.microboot.dto.UserInfoQueryParam;
import com.bupt.microboot.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

/**
 * @Description: mybatis select demo
 * Created by jason on 2022/9/22 11:28
 */
public interface SelectDemoService {
    List<User> list(UserInfoQueryParam userInfoQueryParam);

    User getUser(String userId);
}
