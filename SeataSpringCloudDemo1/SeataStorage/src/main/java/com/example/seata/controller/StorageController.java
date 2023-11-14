package com.example.seata.controller;

import com.example.seata.po.StoragePo;
import com.example.seata.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("decrease")
    public String decrease(@RequestBody StoragePo storagePo) {
        storageService.decrease(storagePo);
        return "库存减少成功";
    }
}
