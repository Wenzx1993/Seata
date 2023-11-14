package com.example.seata.feign.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto {

    private Integer goodsId;

    private Integer storage;

}
