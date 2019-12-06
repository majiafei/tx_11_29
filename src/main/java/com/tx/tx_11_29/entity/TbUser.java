package com.tx.tx_11_29.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@TableName("tb_user")
public class TbUser {

    @TableId(type = IdType.AUTO)
    private Long userId;

    private String userName;

    private Integer userAge;

    private Date createTime;

    private Integer userStatus;

}
