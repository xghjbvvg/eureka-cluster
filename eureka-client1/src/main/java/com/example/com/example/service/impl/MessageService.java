package com.example.com.example.service.impl;

import com.example.com.example.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MessageService implements IMessageService {

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    @Cacheable(value = "message", key = "#userId")
    public Integer getMessageCode(String userId, String phone) {
        System.out.println(6666);
        if (phone.length() != 11) {
            return -1;
        }
        int code = (int) ((Math.random() * 9 + 1) * 10000);
//        redisTemplate.expire(userId,120, TimeUnit.SECONDS);
//        redisTemplat
        redisTemplate.opsForValue().set(userId, code);
        return code;
    }

    @Override
    public Boolean isEquals(String userId, String code) {
        return null;
    }
}
