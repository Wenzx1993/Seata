package com.example.seata.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.example.seata.mapper.PointMapper;
import com.example.seata.po.PointsPo;
import com.example.seata.service.PointService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@LocalTCC
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @TwoPhaseBusinessAction(name = "pointTcc", commitMethod = "increaseCommit", rollbackMethod = "increaseRollback")
    public int increase(@BusinessActionContextParameter("paramPoint") PointsPo pointPo) {
        PointsPo resultPo = ChainWrappers.lambdaQueryChain(pointMapper)
                .eq(PointsPo::getUsername, pointPo.getUsername()).one();
        if (Objects.isNull(resultPo)) {
            pointPo.setFrozenPoints(pointPo.getPoints());
            pointPo.setPoints(0);
            return pointMapper.insert(pointPo);
        }
        resultPo.setFrozenPoints(pointPo.getPoints());
        return pointMapper.updateById(resultPo);
    }


    public boolean increaseCommit(BusinessActionContext actionContext) {
        PointsPo pointPo = actionContext.getActionContext("paramPoint", PointsPo.class);
        PointsPo resultPo = pointMapper.selectById(pointPo.getId());
        if (Objects.nonNull(resultPo)) {
            resultPo.setPoints(resultPo.getPoints() + resultPo.getFrozenPoints());
            resultPo.setFrozenPoints(0);
            pointMapper.updateById(resultPo);
        }
        log.info(resultPo.getId() + "提交成功");
        return true;
    }

    public boolean increaseRollback(BusinessActionContext actionContext) {
        PointsPo pointPo = actionContext.getActionContext("paramPoint", PointsPo.class);
        PointsPo resultPo = pointMapper.selectById(pointPo.getId());
        if (Objects.nonNull(resultPo)) {
            resultPo.setFrozenPoints(0);
            pointMapper.updateById(resultPo);
        }
        log.info(resultPo.getId() + "回滚成功");
        return true;
    }
}
