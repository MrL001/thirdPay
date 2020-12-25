package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.TradeOrderResult;
import com.hntyy.mapper.yeepay.TradeOrderResultMapper;
import com.hntyy.service.yeepay.TradeOrderParamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeOrderParamResultServiceImpl implements TradeOrderParamResultService {

    @Autowired
    private TradeOrderResultMapper tradeOrderResultMapper;

    @Override
    public void insert(TradeOrderResult tradeOrderResult) {
        tradeOrderResultMapper.insert(tradeOrderResult);
    }
}
