package com.tx.tx_11_29.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.tx_11_29.entity.TbStock;
import com.tx.tx_11_29.entity.TbUser;
import com.tx.tx_11_29.mapper.TbStockMapper;
import com.tx.tx_11_29.mapper.TbUserMapper;
import com.tx.tx_11_29.service.ITbStockService;
import com.tx.tx_11_29.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TbStockServiceImpl extends ServiceImpl<TbStockMapper, TbStock> implements ITbStockService {

    @Transactional
    @Override
    public void addStock() {
        TbStock stock = new TbStock();
        stock.setGoodsId(2L);
        stock.setStock(3L);
        this.save(stock);
    }
}
