package com.example.seata.vo;

import lombok.Data;

/**
 * 业务处理类
 */
@Data
public class BusinessOrderVo {

    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品数目
     */
    private Integer num;
    /**
     * 商品总金额
     */
    private Double money;
    /**
     * 下单人
     */
    private String username;

}
