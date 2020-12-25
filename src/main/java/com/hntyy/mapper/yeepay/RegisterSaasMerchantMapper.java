package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParamBackup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterSaasMerchantMapper {

    void insert(RegisterSaasMerchantParamBackup registerSaasMerchantParamBackup);

}
