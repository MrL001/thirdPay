package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.result.TradeOrderResult;

public interface TradeOrderParamResultService {

    void insert(TradeOrderResult tradeOrderResult);

    /**
     * 通过订单id获取交易下单返回结果
     * @param orderId
     * @return
     */
    TradeOrderResult getTradeOrderResultByOrderId(String orderId);

}
