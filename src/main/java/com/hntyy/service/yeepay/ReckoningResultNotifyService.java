package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.result.ReckoningResultNotify;

/**
 * 清算成功回调数据表
 */
public interface ReckoningResultNotifyService {

    void insert(ReckoningResultNotify reckoningResultNotify);

}
