package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.NccashierPayParam;
import com.hntyy.mapper.yeepay.NccashierPayParamMapper;
import com.hntyy.service.yeepay.NccashierPayParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NccashierPayParamServiceImpl implements NccashierPayParamService {

    @Autowired
    private NccashierPayParamMapper nccashierPayParamMapper;

    @Override
    public void insert(NccashierPayParam nccashierPayParam) {
        nccashierPayParamMapper.insert(nccashierPayParam);
    }

}
