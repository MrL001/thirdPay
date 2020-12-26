package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.DivideBackResult;
import com.hntyy.mapper.yeepay.DivideBackResultMapper;
import com.hntyy.service.yeepay.DivideBackResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideBackResultServiceImpl implements DivideBackResultService {

    @Autowired
    private DivideBackResultMapper divideBackResultMapper;

    @Override
    public void insert(DivideBackResult divideBackResult) {
        divideBackResultMapper.insert(divideBackResult);
    }
}
