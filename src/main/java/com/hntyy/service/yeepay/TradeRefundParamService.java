package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.bean.yeepay.query.TradeRefundParam;

/**
 * 申请退款参数
 */
public interface TradeRefundParamService {

    void insert(TradeRefundParam tradeRefundParam);
}
