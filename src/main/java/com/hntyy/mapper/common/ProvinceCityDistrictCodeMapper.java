package com.hntyy.mapper.common;

import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProvinceCityDistrictCodeMapper {

    List<ProvinceCityDistrictCodeEntity> queryListByName(@Param("name") String name,@Param("code") String code);

}
