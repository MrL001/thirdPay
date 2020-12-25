package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.DivideApplyParam;
import com.hntyy.bean.yeepay.query.DivideApplyParamBackup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivideApplyParamMapper {

    void insert(DivideApplyParamBackup divideApplyParamBackup);

}
