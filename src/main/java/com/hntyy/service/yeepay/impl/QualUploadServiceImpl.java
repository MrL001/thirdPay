package com.hntyy.service.yeepay.impl;

import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.mapper.yeepay.QualUploadMapper;
import com.hntyy.service.yeepay.QualUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualUploadServiceImpl implements QualUploadService {

    @Autowired
    private QualUploadMapper qualUploadMapper;

    @Override
    public void insert(QualUploadResult qualUploadResult) {
        qualUploadMapper.insert(qualUploadResult);
    }
}
