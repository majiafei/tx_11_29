package com.tx.tx_11_29.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tx.tx_11_29.entity.TbUser;

public interface ITbUserService extends IService<TbUser> {
    void remark(TbUser user);
}
