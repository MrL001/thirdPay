package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.DivideApplyResult;
import com.hntyy.mapper.yeepay.DivideApplyResultMapper;
import com.hntyy.service.yeepay.DivideApplyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivideApplyResultServiceImpl implements DivideApplyResultService {

    @Autowired
    private DivideApplyResultMapper divideApplyResultMapper;

    @Override
    public void insert(DivideApplyResult divideApplyResult) {
        divideApplyResultMapper.insert(divideApplyResult);
    }
}
