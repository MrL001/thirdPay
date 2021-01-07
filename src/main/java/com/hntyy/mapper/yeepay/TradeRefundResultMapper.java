package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.TradeRefundResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TradeRefundResultMapper {

    void insert(TradeRefundResult tradeRefundResult);

    TradeRefundResult getTradeRefundResultByOrderId(@Param("orderId") String orderId);
}
