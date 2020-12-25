package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterSaasMerchantResultMapper {

    void insert(RegisterSaasMerchantResult registerSaasMerchantResult);

}
