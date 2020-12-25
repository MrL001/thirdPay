package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.bean.yeepay.result.TradeOrderResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderResultMapper {

    void insert(TradeOrderResult tradeOrderResult);

}
