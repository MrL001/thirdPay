package com.hntyy.mapper.common;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BanksCodeMapper {

    List<BanksCodeEntity> queryListByName(@Param("name") String name, @Param("code") String code);

}
