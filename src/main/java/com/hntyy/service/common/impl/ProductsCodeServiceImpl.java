package com.hntyy.service.common.impl;

import com.hntyy.bean.common.ProductsCodeEntity;
import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import com.hntyy.mapper.common.ProductsCodeMapper;
import com.hntyy.mapper.common.ProvinceCityDistrictCodeMapper;
import com.hntyy.service.common.ProductsCodeService;
import com.hntyy.service.common.ProvinceCityDistrictCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsCodeServiceImpl implements ProductsCodeService {

    @Autowired
    private ProductsCodeMapper productsCodeMapper;


    @Override
    public List<ProductsCodeEntity> queryListByName(String name, String code) {
        return productsCodeMapper.queryListByName(name,code);
    }
}
