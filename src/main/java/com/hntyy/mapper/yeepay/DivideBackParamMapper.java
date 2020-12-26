package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.DivideBackParam;
import com.hntyy.bean.yeepay.query.DivideBackParamBackup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideBackParamMapper {

    void insert(DivideBackParamBackup divideBackParamBackup);
}
