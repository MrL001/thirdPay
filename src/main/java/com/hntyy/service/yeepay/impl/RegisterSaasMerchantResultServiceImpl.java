package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import com.hntyy.mapper.yeepay.RegisterSaasMerchantMapper;
import com.hntyy.mapper.yeepay.RegisterSaasMerchantResultMapper;
import com.hntyy.service.yeepay.RegisterSaasMerchantResultService;
import com.hntyy.service.yeepay.RegisterSaasMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterSaasMerchantResultServiceImpl implements RegisterSaasMerchantResultService {

    @Autowired
    private RegisterSaasMerchantResultMapper registerSaasMerchantResultMapper;

    @Override
    public void insert(RegisterSaasMerchantResult registerSaasMerchantResult) {
        registerSaasMerchantResultMapper.insert(registerSaasMerchantResult);
    }
}
