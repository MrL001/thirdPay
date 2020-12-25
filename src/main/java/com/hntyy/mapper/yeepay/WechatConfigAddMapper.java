package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.WechatConfigAddParam;
import com.hntyy.bean.yeepay.query.WechatConfigAddParamBackup;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatConfigAddMapper {

    void insert(WechatConfigAddParamBackup wechatConfigAddParamBackup);

}
