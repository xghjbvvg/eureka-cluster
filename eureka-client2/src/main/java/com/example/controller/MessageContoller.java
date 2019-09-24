package com.example.controller;

import com.example.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageContoller {
    @Autowired
    IMessageService messageService;
    @Value("${server.port}")
    int port;

    @RequestMapping("/getMessage")
    public String getMessage(String userId, String phone) {
        return "Hello," + userId + "，端口为：" + port + "你的验证码为：" + messageService.getMessageCode(userId, phone);
    }
}
