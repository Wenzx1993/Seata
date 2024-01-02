package com.example.seata.controller;

import com.example.seata.po.PointsPo;
import com.example.seata.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("increase")
    public String increase(@RequestBody PointsPo pointsPo) {
        pointService.increase(pointsPo);
        return "积分增加成功";
    }
}
