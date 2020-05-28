package com.tx.tx_11_29.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tx.tx_11_29.entity.TbStock;
import com.tx.tx_11_29.entity.TbUser;

public interface ITbStockService extends IService<TbStock> {
    void addStock();
}
