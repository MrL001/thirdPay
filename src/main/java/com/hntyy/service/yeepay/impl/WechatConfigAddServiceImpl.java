package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.WechatConfigAddParam;
import com.hntyy.bean.yeepay.query.WechatConfigAddParamBackup;
import com.hntyy.mapper.yeepay.WechatConfigAddMapper;
import com.hntyy.service.yeepay.WechatConfigAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公众号配置参数
 */
@Service
public class WechatConfigAddServiceImpl implements WechatConfigAddService {

    @Autowired
    private WechatConfigAddMapper wechatConfigAddMapper;

    @Override
    public void insert(WechatConfigAddParamBackup wechatConfigAddParamBackup) {
        wechatConfigAddMapper.insert(wechatConfigAddParamBackup);
    }
}
