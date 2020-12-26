package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.TradeRefundNotifyResult;
import com.hntyy.mapper.yeepay.TradeRefundNotifyResultMapper;
import com.hntyy.service.yeepay.TradeRefundNotifyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeRefundNotifyResultServiceImpl implements TradeRefundNotifyResultService {

    @Autowired
    private TradeRefundNotifyResultMapper tradeRefundNotifyResultMapper;

    @Override
    public void insert(TradeRefundNotifyResult tradeRefundNotifyResult) {
        tradeRefundNotifyResultMapper.insert(tradeRefundNotifyResult);
    }
}
