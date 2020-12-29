package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.query.NotifyUrlEntity;
import com.hntyy.mapper.yeepay.NotifyUrlMapper;
import com.hntyy.service.yeepay.NotifyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyUrlServiceImpl implements NotifyUrlService {

    @Autowired
    private NotifyUrlMapper notifyUrlMapper;

    @Override
    public void insert(NotifyUrlEntity notifyUrlEntity) {
        notifyUrlMapper.insert(notifyUrlEntity);
    }

    @Override
    public NotifyUrlEntity getNotifyUrl(String requestId,Integer type) {
        return notifyUrlMapper.getNotifyUrl(requestId,type);
    }
}
