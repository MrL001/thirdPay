package com.hntyy.service.common;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;

import java.util.List;

/**
 * 获取省市区编码
 */
public interface BanksCodeService {

    List<BanksCodeEntity> queryListByName(String name, String code);

}
