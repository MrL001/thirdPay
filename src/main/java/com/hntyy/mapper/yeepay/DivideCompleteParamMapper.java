package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.DivideCompleteParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideCompleteParamMapper {
    void insert(DivideCompleteParam divideCompleteParam);
}
