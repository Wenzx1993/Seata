package com.example.seata.controller;

import com.example.seata.service.BusinessService;
import com.example.seata.vo.BusinessOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 下单操作
     * @param businessOrderVo
     */
    @PostMapping("order")
    public String order(@RequestBody BusinessOrderVo businessOrderVo) {
        businessService.order(businessOrderVo);
        return "下单成功";
    }
}
