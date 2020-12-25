package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.NccashierPayResult;
import com.hntyy.mapper.yeepay.NccashierPayResultMapper;
import com.hntyy.service.yeepay.NccashierPayResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NccashierPayResultServiceImpl implements NccashierPayResultService {

    @Autowired
    private NccashierPayResultMapper nccashierPayResultMapper;

    @Override
    public void insert(NccashierPayResult nccashierPayResult) {
        nccashierPayResultMapper.insert(nccashierPayResult);
    }
}
