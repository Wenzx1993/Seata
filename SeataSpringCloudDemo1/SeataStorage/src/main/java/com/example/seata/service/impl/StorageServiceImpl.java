package com.example.seata.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.example.seata.mapper.StorageMapper;
import com.example.seata.po.StoragePo;
import com.example.seata.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    public int decrease(StoragePo storagePo) {
        int a = 2/0;
        StoragePo resultPo = ChainWrappers.lambdaQueryChain(storageMapper)
                .eq(StoragePo::getGoodsId, storagePo.getGoodsId()).one();
        resultPo.setStorage(resultPo.getStorage()-storagePo.getStorage());
        return storageMapper.updateById(resultPo);
    }
}
