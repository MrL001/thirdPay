package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.DivideCompleteParam;
import com.hntyy.mapper.yeepay.DivideCompleteParamMapper;
import com.hntyy.service.yeepay.DivideCompleteParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideCompleteParamServiceImpl implements DivideCompleteParamService {

    @Autowired
    private DivideCompleteParamMapper divideCompleteParamMapper;

    @Override
    public void insert(DivideCompleteParam divideCompleteParam) {
        divideCompleteParamMapper.insert(divideCompleteParam);
    }
}
