package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.result.QualUploadResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QualUploadMapper {

    void insert(QualUploadResult qualUploadResult);

}
