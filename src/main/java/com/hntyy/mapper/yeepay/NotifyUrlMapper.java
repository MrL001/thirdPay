package com.hntyy.mapper.yeepay;

import com.hntyy.bean.yeepay.query.NotifyUrlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotifyUrlMapper {

    void insert(NotifyUrlEntity notifyUrlEntity);

    NotifyUrlEntity getNotifyUrl(@Param("requestId") String requestId, @Param("type") Integer type);
}
