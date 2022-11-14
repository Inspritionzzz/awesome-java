package com.bupt.microbootredis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接
        jedisPoolConfig.setMaxTotal(8);
        // 设置最大空闲连接
        jedisPoolConfig.setMaxIdle(8);
        // 设置最小空闲连接
        jedisPoolConfig.setMinIdle(0);
        // 设置最长等待时间，ms
        jedisPoolConfig.setMaxWaitMillis(200);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.0.102", 6379, 1000, "100037");
    }

    // 获取 jedis 对象
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}


