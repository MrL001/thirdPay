package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.WechatConfigAddParam;
import com.hntyy.bean.yeepay.result.WechatConfigAddResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatConfigAddResultMapper {

    void insert(WechatConfigAddResult wechatConfigAddResult);

}
