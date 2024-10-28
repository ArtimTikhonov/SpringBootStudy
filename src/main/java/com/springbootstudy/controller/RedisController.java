package com.springbootstudy.controller;

import com.springbootstudy.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/")
public class RedisController {

    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    // Установка значения без TTL
    @GetMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "Value set!";
    }

    // Установка значения с TTL (10 секунд)
    @GetMapping("/setWithTTL")
    public String setKeyWithTTL(@RequestParam String key, @RequestParam String value) {
        redisService.setValueWithTTL(key, value, 10, TimeUnit.SECONDS);
        return "Value set with TTL!";
    }

    // Получение значения по ключу
    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        String value = redisService.getValue(key);
        return value != null ? "Stored value: " + value : "Value not found";
    }

    // Удаление значения
    @GetMapping("/delete")
    public String deleteKey(@RequestParam String key) {
        redisService.deleteValue(key);
        return "Value deleted!";
    }
}