package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.TradeRefundResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeRefundResultMapper {

    void insert(TradeRefundResult tradeRefundResult);
}
