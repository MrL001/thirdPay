package com.hntyy.service.common;

import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import com.hntyy.bean.yeepay.result.QualUploadResult;

import java.util.List;

/**
 * 获取省市区编码
 */
public interface ProvinceCityDistrictCodeService {

    List<ProvinceCityDistrictCodeEntity> queryListByName(String name,String code);

}
