package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.DivideCompleteResult;
import com.hntyy.mapper.yeepay.DivideCompleteResultMapper;
import com.hntyy.service.yeepay.DivideCompleteResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideCompleteResultServiceImpl implements DivideCompleteResultService {

    @Autowired
    private DivideCompleteResultMapper divideCompleteResultMapper;

    @Override
    public void insert(DivideCompleteResult divideCompleteResult) {
        divideCompleteResultMapper.insert(divideCompleteResult);
    }
}
