package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.NccashierPayParam;
import com.hntyy.bean.yeepay.result.PayResultNotify;

/**
 * 支付结果通知回调数据表
 */
public interface PayResultNotifyService {

    void insert(PayResultNotify payResultNotify);

}
