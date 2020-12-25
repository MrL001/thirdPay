package com.hntyy.service.common;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProductsCodeEntity;

import java.util.List;

/**
 * 获取产品编码
 */
public interface ProductsCodeService {

    List<ProductsCodeEntity> queryListByName(String name, String code);

}
