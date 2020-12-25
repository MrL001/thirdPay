package com.hntyy.service.common.impl;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import com.hntyy.mapper.common.BanksCodeMapper;
import com.hntyy.mapper.common.ProvinceCityDistrictCodeMapper;
import com.hntyy.service.common.BanksCodeService;
import com.hntyy.service.common.ProvinceCityDistrictCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanksCodeServiceImpl implements BanksCodeService {

    @Autowired
    private BanksCodeMapper banksCodeMapper;

    @Override
    public List<BanksCodeEntity> queryListByName(String name, String code) {
        return banksCodeMapper.queryListByName(name,code);
    }
}
