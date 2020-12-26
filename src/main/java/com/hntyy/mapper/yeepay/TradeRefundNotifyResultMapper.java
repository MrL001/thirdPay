package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.TradeRefundNotifyResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeRefundNotifyResultMapper {

    void insert(TradeRefundNotifyResult tradeRefundNotifyResult);

}
