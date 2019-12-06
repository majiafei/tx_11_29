package com.tx.tx_11_29.controller;

import com.tx.tx_11_29.entity.TbUser;
import com.tx.tx_11_29.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ITbUserService userService;

    private Map<Long, Long> map = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/remark")
    public String remarkUser(@RequestBody TbUser user) {
//        if (map.putIfAbsent(user.getUserId(), user.getUserId()) != null) {
//            System.out.println("已经在处理");
//            return "已经在处理";
//        }

        Boolean isGetLock = stringRedisTemplate.opsForValue().setIfAbsent("remarkUserLock", String.valueOf(user.getUserId()));
        if (!isGetLock) {
            return "正在处理";
        }

        try {
            userService.remark(user);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        } finally {
//            map.remove(user.getUserId());
            stringRedisTemplate.delete("remarkUserLock");
        }

    }
}
