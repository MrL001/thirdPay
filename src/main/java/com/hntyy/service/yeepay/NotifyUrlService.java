package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.NotifyUrlEntity;

/**
 * 请求地址
 */
public interface NotifyUrlService {

    void insert(NotifyUrlEntity notifyUrlEntity);

    NotifyUrlEntity getNotifyUrl(String requestId,Integer type);

}
