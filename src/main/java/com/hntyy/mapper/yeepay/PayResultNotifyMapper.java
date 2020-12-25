package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.NccashierPayParam;
import com.hntyy.bean.yeepay.result.PayResultNotify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayResultNotifyMapper {

    void insert(PayResultNotify payResultNotify);

}
