package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.WechatConfigAddResult;
import com.hntyy.mapper.yeepay.WechatConfigAddResultMapper;
import com.hntyy.service.yeepay.WechatConfigAddResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公众号配置结果
 */
@Service
public class WechatConfigAddResultServiceImpl implements WechatConfigAddResultService {

    @Autowired
    private WechatConfigAddResultMapper wechatConfigAddResultMapper;

    @Override
    public void insert(WechatConfigAddResult wechatConfigAddResult) {
        wechatConfigAddResultMapper.insert(wechatConfigAddResult);
    }
}
