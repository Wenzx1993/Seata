package com.example.seata.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单类
 */
@Data
@TableName("t_order")
public class OrderPo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer goodsId;

    private Integer num;

    private Double money;

    private String username;

    private Integer status;

    private Date createTime;

}
