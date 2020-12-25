package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.ReckoningResultNotify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReckoningResultNotifyMapper {

    void insert(ReckoningResultNotify reckoningResultNotify);

}
