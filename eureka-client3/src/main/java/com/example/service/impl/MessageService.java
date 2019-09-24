package com.example.service.impl;

import com.example.service.IMessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

    //    @Autowired
//    RedisTemplate redisTemplate;
    @Override
    public Integer getMessageCode(String userId, String phone) {
        if (phone.length() != 11) {
            return -1;
        }
        int code = (int) ((Math.random() * 9 + 1) * 10000);
        return code;
    }

    @Override
    public Boolean isEquals(String userId, String code) {
        return null;
    }
}
