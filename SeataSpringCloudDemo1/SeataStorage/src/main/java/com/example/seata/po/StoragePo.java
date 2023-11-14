package com.example.seata.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 库存Po
 */
@Data
@TableName("t_storage")
public class StoragePo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField
    private Integer goodsId;

    @TableField
    private Integer storage;

}
