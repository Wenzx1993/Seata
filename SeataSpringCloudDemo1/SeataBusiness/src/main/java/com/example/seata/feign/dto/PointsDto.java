package com.example.seata.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 积分Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsDto {

    private String username;

    private Integer points;

}
