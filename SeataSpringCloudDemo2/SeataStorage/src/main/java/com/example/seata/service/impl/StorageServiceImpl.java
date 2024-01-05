package com.example.seata.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.example.seata.mapper.StorageMapper;
import com.example.seata.po.StoragePo;
import com.example.seata.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@LocalTCC
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @TwoPhaseBusinessAction(name = "storageTcc", commitMethod = "decreaseCommit", rollbackMethod = "decreaseRollback")
    public int decrease(@BusinessActionContextParameter(paramName = "paramStorage") StoragePo storagePo) {
        StoragePo resultPo = ChainWrappers.lambdaQueryChain(storageMapper)
                .eq(StoragePo::getGoodsId, storagePo.getGoodsId()).one();
        resultPo.setFrozenStorage(storagePo.getStorage());
        return storageMapper.updateById(resultPo);
    }

    public boolean decreaseCommit(BusinessActionContext actionContext) {
        StoragePo storagePo = actionContext.getActionContext("paramStorage", StoragePo.class);
        StoragePo resultPo = ChainWrappers.lambdaQueryChain(storageMapper)
                .eq(StoragePo::getGoodsId, storagePo.getGoodsId()).one();
        if (Objects.nonNull(resultPo)) {
            resultPo.setStorage(resultPo.getStorage() - resultPo.getFrozenStorage());
            resultPo.setFrozenStorage(0);
            storageMapper.updateById(resultPo);
        }
        log.info(resultPo.getId() + "提交成功");
        return true;
    }

    public boolean decreaseRollback(BusinessActionContext actionContext) {
        StoragePo storagePo = actionContext.getActionContext("paramStorage", StoragePo.class);
        StoragePo resultPo = ChainWrappers.lambdaQueryChain(storageMapper)
                .eq(StoragePo::getGoodsId, storagePo.getGoodsId()).one();
        if (Objects.nonNull(resultPo)) {
            resultPo.setFrozenStorage(0);
            storageMapper.updateById(resultPo);
        }
        log.info(resultPo.getId() + "回滚成功");
        return true;
    }
}
