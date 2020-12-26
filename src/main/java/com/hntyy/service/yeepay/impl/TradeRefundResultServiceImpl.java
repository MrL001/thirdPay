package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.bean.yeepay.result.TradeRefundResult;
import com.hntyy.mapper.yeepay.TradeRefundResultMapper;
import com.hntyy.service.yeepay.TradeRefundResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeRefundResultServiceImpl implements TradeRefundResultService {

    @Autowired
    private TradeRefundResultMapper tradeRefundResultMapper;

    @Override
    public void insert(TradeRefundResult tradeRefundResult) {
        tradeRefundResultMapper.insert(tradeRefundResult);
    }
}
