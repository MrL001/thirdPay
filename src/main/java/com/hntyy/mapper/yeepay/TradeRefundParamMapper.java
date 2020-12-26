package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.TradeRefundParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeRefundParamMapper {

    void insert(TradeRefundParam tradeRefundParam);
}
