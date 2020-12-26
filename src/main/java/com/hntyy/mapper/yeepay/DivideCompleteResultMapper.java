package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.DivideCompleteResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideCompleteResultMapper {
    void insert(DivideCompleteResult divideCompleteResult);
}
