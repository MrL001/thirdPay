package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.DivideBackResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideBackResultMapper {

    void insert(DivideBackResult divideBackResult);
}
