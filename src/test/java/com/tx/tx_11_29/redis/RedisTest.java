package com.tx.tx_11_29.redis;

import com.tx.tx_11_29.Tx1129ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RedisTest extends Tx1129ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testGet() {
        AtomicReference<Integer> atomicInteger = new AtomicReference(100);
        System.out.println(atomicInteger.get());
        //String aa = stringRedisTemplate.opsForValue().get("aa");
       // Assert.notNull(aa, "");
    }

}
