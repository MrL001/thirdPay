package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.TradeOrderResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TradeOrderResultMapper {

    void insert(TradeOrderResult tradeOrderResult);

    TradeOrderResult getTradeOrderResultByOrderId(@Param("orderId") String orderId);

}
