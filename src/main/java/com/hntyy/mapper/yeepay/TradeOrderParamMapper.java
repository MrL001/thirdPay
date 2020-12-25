package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.TradeOrderParam;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderParamMapper {

    void insert(TradeOrderParam tradeOrderParam);

}
