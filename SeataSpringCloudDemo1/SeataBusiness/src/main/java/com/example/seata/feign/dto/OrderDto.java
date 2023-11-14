package com.example.seata.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer goodsId;

    private Integer num;

    private Double money;

    private String username;

}
