package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.result.TradeRefundNotifyResult;

/**
 * 申请退款回调地址返回结果
 */
public interface TradeRefundNotifyResultService {

    void insert(TradeRefundNotifyResult tradeRefundNotifyResult);

}
