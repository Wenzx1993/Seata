package com.example.seata.service.impl;

import com.example.seata.mapper.OrderMapper;
import com.example.seata.po.OrderPo;
import com.example.seata.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@LocalTCC
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 两阶段提交
     *
     * @param orderPo
     * @return
     */
    @TwoPhaseBusinessAction(name = "orderTcc", commitMethod = "addOneCommit", rollbackMethod = "addOneRollback")
    public int addOne(@BusinessActionContextParameter(paramName = "orderParam") OrderPo orderPo) {
        orderPo.setCreateTime(new Date());
        orderPo.setStatus(0);
        return orderMapper.insert(orderPo);
    }

    public boolean addOneCommit(BusinessActionContext actionContext) {
        OrderPo orderPo = actionContext.getActionContext("orderParam", OrderPo.class);
        OrderPo resultPo = orderMapper.selectById(orderPo.getId());
        if (Objects.nonNull(resultPo)) {
            resultPo.setStatus(1);
            orderMapper.updateById(resultPo);
        }
        log.info(orderPo.getId() + "提交成功");
        return true;
    }

    public boolean addOneRollback(BusinessActionContext actionContext) {
        OrderPo orderPo = actionContext.getActionContext("orderParam", OrderPo.class);
        OrderPo resultPo = orderMapper.selectById(orderPo.getId());
        if (Objects.nonNull(resultPo)) {
            orderMapper.deleteById(resultPo);
        }
        log.info(orderPo.getId() + "回滚成功");
        return true;
    }
}
