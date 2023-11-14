package com.example.seata.feign;

import com.example.seata.feign.dto.PointsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("points")
public interface PointsFeign {

    @PostMapping("/points/increase")
    public String increase(@RequestBody PointsDto pointsDto);
}
