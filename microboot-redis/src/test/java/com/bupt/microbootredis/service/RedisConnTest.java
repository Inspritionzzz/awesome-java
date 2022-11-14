package com.bupt.microbootredis.service;

import com.bupt.microbootredis.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisConnTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisServiceImpl redisService;

    @Test
    public void redisTest() {
        System.out.println(redisService.get("newCRM_2-SESSION_MAX_AGE"));
    }

    @Test
    public void redisConnTest() {
        int time = (Integer)redisTemplate.opsForValue().get("newCRM_2-SESSION_MAX_AGE");
        System.out.println(time);
    }
}
