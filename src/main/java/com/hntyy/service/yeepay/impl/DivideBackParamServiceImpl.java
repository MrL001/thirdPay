package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.DivideBackParam;
import com.hntyy.bean.yeepay.query.DivideBackParamBackup;
import com.hntyy.mapper.yeepay.DivideBackParamMapper;
import com.hntyy.service.yeepay.DivideBackParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideBackParamServiceImpl implements DivideBackParamService {

    @Autowired
    private DivideBackParamMapper divideBackParamMapper;

    @Override
    public void insert(DivideBackParamBackup divideBackParamBackup) {
        divideBackParamMapper.insert(divideBackParamBackup);
    }
}
