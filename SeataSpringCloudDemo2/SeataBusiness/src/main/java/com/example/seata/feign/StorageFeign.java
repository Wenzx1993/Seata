package com.example.seata.feign;

import com.example.seata.feign.dto.StorageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("storage")
public interface StorageFeign {

    @PostMapping("/storage/decrease")
    public String decrease(@RequestBody StorageDto storageDto);
}
