package com.example.seata.feign;

import com.example.seata.feign.dto.PointDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("point")
public interface PointFeign {

    @PostMapping("/point/increase")
    public String increase(@RequestBody PointDto pointsDto);
}
