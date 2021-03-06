package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.bean.yeepay.result.TradeRefundResult;

/**
 * 申请退款返回结果
 */
public interface TradeRefundResultService {

    void insert(TradeRefundResult tradeRefundResult);

    /**
     * 通过订单id查询申请退款返回结果
     * @param orderId
     * @return
     */
    TradeRefundResult getTradeRefundResultByOrderId(String orderId);

}
