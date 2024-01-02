package com.example.seata.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 积分Po类
 */
@Data
@TableName("t_points")
public class PointsPo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private String username;

    @TableField
    private Integer points;

    @TableField("frozen_points")
    private Integer frozenPoints;

}
