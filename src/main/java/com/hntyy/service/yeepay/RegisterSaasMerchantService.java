package com.hntyy.service.yeepay;


import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParamBackup;

/**
 * 商户入网参数
 */
public interface RegisterSaasMerchantService {

    void insert(RegisterSaasMerchantParamBackup  registerSaasMerchantParamBackup);

}
