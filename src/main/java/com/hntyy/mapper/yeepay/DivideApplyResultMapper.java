package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.DivideApplyResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideApplyResultMapper {

    void insert(DivideApplyResult divideApplyResult);

}
