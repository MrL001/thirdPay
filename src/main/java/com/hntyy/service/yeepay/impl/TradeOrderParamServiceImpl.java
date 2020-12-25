package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.mapper.yeepay.TradeOrderParamMapper;
import com.hntyy.service.yeepay.TradeOrderParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeOrderParamServiceImpl implements TradeOrderParamService {

    @Autowired
    private TradeOrderParamMapper tradeOrderParamMapper;

    @Override
    public void insert(TradeOrderParam tradeOrderParam) {
        tradeOrderParamMapper.insert(tradeOrderParam);
    }
}
