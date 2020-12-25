package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.DivideApplyParam;
import com.hntyy.bean.yeepay.query.DivideApplyParamBackup;
import com.hntyy.mapper.yeepay.DivideApplyParamMapper;
import com.hntyy.service.yeepay.DivideApplyParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideApplyParamServiceImpl implements DivideApplyParamService {

    @Autowired
    private DivideApplyParamMapper divideApplyParamMapper;

    @Override
    public void insert(DivideApplyParamBackup divideApplyParamBackup) {
        divideApplyParamMapper.insert(divideApplyParamBackup);
    }
}
