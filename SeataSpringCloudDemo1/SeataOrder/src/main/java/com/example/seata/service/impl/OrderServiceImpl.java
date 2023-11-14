package com.example.seata.service.impl;

import com.example.seata.mapper.OrderMapper;
import com.example.seata.po.OrderPo;
import com.example.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int addOne(OrderPo orderPo) {

        return orderMapper.insert(orderPo);
    }
}
