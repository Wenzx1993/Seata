package com.example.seata.feign;

import com.example.seata.feign.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order")
public interface OrderFeign {

    @PostMapping("/order/one")
    public String addOne(@RequestBody OrderDto orderDto);
}
