package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.ReckoningResultNotify;
import com.hntyy.mapper.yeepay.ReckoningResultNotifyMapper;
import com.hntyy.service.yeepay.ReckoningResultNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReckoningResultNotifyServiceImpl implements ReckoningResultNotifyService {

    @Autowired
    private ReckoningResultNotifyMapper reckoningResultNotifyMapper;

    @Override
    public void insert(ReckoningResultNotify reckoningResultNotify) {
        reckoningResultNotifyMapper.insert(reckoningResultNotify);
    }
}
