package com.tx.tx_11_29.redis;

import com.tx.tx_11_29.Tx1129ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

public class RedisTest extends Tx1129ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testGet() {
        String aa = stringRedisTemplate.opsForValue().get("aa");
        Assert.notNull(aa, "");
    }

}
