package com.tx.tx_11_29.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.tx_11_29.entity.TbUser;
import com.tx.tx_11_29.mapper.TbUserMapper;
import com.tx.tx_11_29.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Autowired
    private TbUserMapper userMapper;



    @Override
    public void remark(TbUser user) {
        TbUser tbUser = userMapper.selectById(user.getUserId());

        if (tbUser == null) {
            throw new RuntimeException("该用户不存在");
        }
        if (tbUser.getUserStatus() == 1) {
            throw new RuntimeException("已经审核过");
        }
        tbUser.setUserStatus(1);
        this.updateById(tbUser);

        TbUser newUser = new TbUser();
        newUser.setCreateTime(new Date());
        newUser.setUserStatus(0);
        newUser.setUserAge(11);
        newUser.setUserName("remark");
        this.save(newUser);

    }
}
