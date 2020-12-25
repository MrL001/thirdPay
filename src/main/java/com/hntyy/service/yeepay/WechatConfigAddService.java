package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.WechatConfigAddParam;
import com.hntyy.bean.yeepay.query.WechatConfigAddParamBackup;

/**
 * 公众号配置参数
 */
public interface WechatConfigAddService {

    void insert(WechatConfigAddParamBackup wechatConfigAddParamBackup);

}
