package com.bupt.microbootredis.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Map;

@SpringBootTest
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1. 建立连接
        // jedis = new Jedis("192.168.0.102", 6379);
        jedis = JedisConnectionFactory.getJedis();
        // 2. 设置密码
        jedis.auth("100037");
        // 3. 选择数据库
        jedis.select(0);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    void StirngTest() {
        String result = jedis.set("name", "jason2");
        System.out.println("result = " + result);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void HashTest() {
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "18");
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println("result = " + map.toString());
    }

}