package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParamBackup;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.mapper.yeepay.RegisterSaasMerchantMapper;
import com.hntyy.service.yeepay.RegisterSaasMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterSaasMerchantServiceImpl implements RegisterSaasMerchantService {

    @Autowired
    private RegisterSaasMerchantMapper registerSaasMerchantMapper;

    @Override
    public void insert(RegisterSaasMerchantParamBackup registerSaasMerchantParamBackup) {
        registerSaasMerchantMapper.insert(registerSaasMerchantParamBackup);
    }
}
