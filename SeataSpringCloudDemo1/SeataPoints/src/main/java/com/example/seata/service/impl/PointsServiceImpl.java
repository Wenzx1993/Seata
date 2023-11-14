package com.example.seata.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.example.seata.mapper.PointsMapper;
import com.example.seata.po.PointsPo;
import com.example.seata.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    private PointsMapper pointsMapper;

    public int increase(PointsPo pointsPo) {
        PointsPo resultPo = ChainWrappers.lambdaQueryChain(pointsMapper)
                .eq(PointsPo::getUsername, pointsPo.getUsername()).one();
        if(Objects.isNull(resultPo)) {
           return pointsMapper.insert(pointsPo);
        }
        resultPo.setPoints(pointsPo.getPoints()+resultPo.getPoints());
        return pointsMapper.updateById(resultPo);
    }
}
