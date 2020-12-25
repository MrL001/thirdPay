package com.hntyy.mapper.common;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProductsCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsCodeMapper {

    List<ProductsCodeEntity> queryListByName(@Param("name") String name, @Param("code") String code);

}
