package com.bupt.microbootredis.demo;

import com.bupt.microbootredis.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void StringTest() {
        redisTemplate.opsForValue().set("name2", "jason3");
        Object name = redisTemplate.opsForValue().get("name2");
        System.out.println("name2 = " + name);
    }

    @Test
    void saveUserTest() {
        // 会自动将对象序列化成json
        redisTemplate.opsForValue().set("user1", new User("jason", 18));

        // 会自动将对象反序列化成对象
        User user = (User)redisTemplate.opsForValue().get("user1");
        System.out.println("user = " + user);
    }

    @Test
    void saveUserTest2() throws JsonProcessingException {
        User user = new User("jason", 18);
        // 手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user2", json);
        String userjson = stringRedisTemplate.opsForValue().get("user2");
        // 手动反序列化
        User user2 = mapper.readValue(userjson, User.class);
        System.out.println("user2 = " + user2);
    }

    @Test
    void hashTest() {
        stringRedisTemplate.opsForHash().put("user3", "name", "Jams");
        stringRedisTemplate.opsForHash().put("user3", "age", "21");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user3");
        System.out.println("entries = " + entries);
    }
}
