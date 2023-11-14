package com.example.seata.service.impl;

import com.example.seata.feign.OrderFeign;
import com.example.seata.feign.PointsFeign;
import com.example.seata.feign.StorageFeign;
import com.example.seata.feign.dto.OrderDto;
import com.example.seata.feign.dto.PointsDto;
import com.example.seata.feign.dto.StorageDto;
import com.example.seata.service.BusinessService;
import com.example.seata.vo.BusinessOrderVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private PointsFeign pointsFeign;

    @Autowired
    private StorageFeign storageFeign;

    @GlobalTransactional(name = "order")
    public void order(BusinessOrderVo businessOrderVo) {
        Integer goodsId = businessOrderVo.getGoodsId();
        Integer num = businessOrderVo.getNum();
        String username = businessOrderVo.getUsername();
        OrderDto orderDto = new OrderDto(goodsId, num, businessOrderVo.getMoney(), username);
        orderFeign.addOne(orderDto);


        PointsDto pointsDto = new PointsDto(username, num);
        pointsFeign.increase(pointsDto);


        StorageDto storageDto = new StorageDto(goodsId, num);
        storageFeign.decrease(storageDto);
    }
}
