package com.tx.tx_11_29.mapper;

import com.tx.tx_11_29.Tx1129ApplicationTests;
import com.tx.tx_11_29.entity.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends Tx1129ApplicationTests {

    @Autowired
    private TbUserMapper userMapper;

    @Test
    public void testGet() {
        TbUser tbUser = userMapper.selectById(1L);
        System.out.println(tbUser == null);
    }

}
