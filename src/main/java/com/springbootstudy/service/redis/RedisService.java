package com.springbootstudy.service.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValueWithTTL(String key, String value, long timeout, TimeUnit timeUnit ) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
