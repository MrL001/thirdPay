package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.bean.yeepay.query.TradeRefundParam;
import com.hntyy.mapper.yeepay.TradeRefundParamMapper;
import com.hntyy.service.yeepay.TradeRefundParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeRefundParamServiceImpl implements TradeRefundParamService {

    @Autowired
    private TradeRefundParamMapper tradeRefundParamMapper;

    @Override
    public void insert(TradeRefundParam tradeRefundParam) {
        tradeRefundParamMapper.insert(tradeRefundParam);
    }
}
