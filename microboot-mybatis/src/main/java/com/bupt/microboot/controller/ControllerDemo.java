package com.bupt.microboot.controller;

import com.bupt.microboot.dto.UserInfoQueryParam;
import com.bupt.microboot.model.User;
import com.bupt.microboot.service.SelectDemoService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * Created by jason on 2022/9/21 16:44
 */

@RestController
//@EnableAutoConfiguration
public class ControllerDemo {

    @Resource
    SelectDemoService selectDemoService;

    @Resource
    UserInfoQueryParam userInfoQueryParam;

//    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @GetMapping(value = "/index")
    @ResponseBody
    public List<User> ControllerDemo(@RequestParam String userId,
                                     @RequestParam String userName) {
        userInfoQueryParam.setUserId(userId);
        userInfoQueryParam.setUserId(userName);
        return selectDemoService.list(userInfoQueryParam);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public List<User> getUser(@RequestParam String userId,
                              @RequestParam String userName) {
        userInfoQueryParam.setUserId(userId);
        userInfoQueryParam.setUserId(userName);
        return selectDemoService.list(userInfoQueryParam);
    }


}
