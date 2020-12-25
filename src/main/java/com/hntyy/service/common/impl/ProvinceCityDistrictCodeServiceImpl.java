package com.hntyy.service.common.impl;

import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.mapper.common.ProvinceCityDistrictCodeMapper;
import com.hntyy.service.common.ProvinceCityDistrictCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceCityDistrictCodeServiceImpl implements ProvinceCityDistrictCodeService {

    @Autowired
    private ProvinceCityDistrictCodeMapper provinceCityDistrictCodeMapper;

    @Override
    public List<ProvinceCityDistrictCodeEntity> queryListByName(String name,String code) {
        return provinceCityDistrictCodeMapper.queryListByName(name,code);
    }
}
