package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.PayResultNotify;
import com.hntyy.mapper.yeepay.PayResultNotifyMapper;
import com.hntyy.service.yeepay.PayResultNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayResultNotifyServiceImpl implements PayResultNotifyService {

    @Autowired
    private PayResultNotifyMapper payResultNotifyMapper;

    @Override
    public void insert(PayResultNotify payResultNotify) {
        payResultNotifyMapper.insert(payResultNotify);
    }
}
