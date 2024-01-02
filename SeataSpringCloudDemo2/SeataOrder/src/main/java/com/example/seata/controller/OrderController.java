package com.example.seata.controller;

import com.example.seata.po.OrderPo;
import com.example.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("one")
    public String addOne(@RequestBody OrderPo orderPo) {
        orderService.addOne(orderPo);
        return "添加成功";
    }
}
