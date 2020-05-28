package com.tx.tx_11_29.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: TbStock
 * @Auther: admin
 * @Date: 2020/5/28 10:42
 * @Description:
 */
@Data
@TableName("tb_stock")
public class TbStock {

    @TableId
    @TableField("tb_stock_id")
    private Long tbStockId;

    @TableField("stock")
    private Long stock;

    @TableField("goods_id")
    private Long goodsId;

}
