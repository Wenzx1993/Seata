package com.example.seata.controller;

import com.example.seata.po.PointsPo;
import com.example.seata.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("points")
public class PointsController {

    @Autowired
    private PointsService pointsService;

    @PostMapping("increase")
    public String increase(@RequestBody PointsPo pointsPo) {
        pointsService.increase(pointsPo);
        return "积分增加成功";
    }
}
