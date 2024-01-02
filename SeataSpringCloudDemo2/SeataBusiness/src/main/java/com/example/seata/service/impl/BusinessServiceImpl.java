package com.example.seata.service.impl;

import com.example.seata.feign.OrderFeign;
import com.example.seata.feign.PointFeign;
import com.example.seata.feign.StorageFeign;
import com.example.seata.feign.dto.OrderDto;
import com.example.seata.feign.dto.PointDto;
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
    private PointFeign pointsFeign;

    @Autowired
    private StorageFeign storageFeign;

    @GlobalTransactional(name = "sale",timeoutMills = 100000,rollbackFor = Exception.class)
    public void order(BusinessOrderVo businessOrderVo) {
        Integer goodsId = businessOrderVo.getGoodsId();
        Integer num = businessOrderVo.getNum();
        String username = businessOrderVo.getUsername();
        OrderDto orderDto = new OrderDto(goodsId, num, businessOrderVo.getMoney(), username);
        orderFeign.addOne(orderDto);


        PointDto pointsDto = new PointDto(username, num);
        pointsFeign.increase(pointsDto);


        StorageDto storageDto = new StorageDto(goodsId, num);
        storageFeign.decrease(storageDto);
    }
}
