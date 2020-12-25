package com.hntyy.controller.common;

import com.hntyy.bean.common.BanksCodeEntity;
import com.hntyy.bean.common.ProductsCodeEntity;
import com.hntyy.bean.common.ProvinceCityDistrictCodeEntity;
import com.hntyy.service.common.BanksCodeService;
import com.hntyy.service.common.ProductsCodeService;
import com.hntyy.service.common.ProvinceCityDistrictCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/commonCode")
@Api(tags = "获取各种编码")
public class CommonCodeController {

    @Autowired
    private ProvinceCityDistrictCodeService provinceCityDistrictCodeService;

    @Autowired
    private BanksCodeService banksCodeService;

    @Autowired
    private ProductsCodeService productsCodeService;

    @ApiOperation(value="获取省市区编码")
    @RequestMapping(value = "/queryProvinceCityDistrictCode",method = RequestMethod.GET)
    public List<ProvinceCityDistrictCodeEntity> queryProvinceCityDistrictCode(String name,String code){
        List<ProvinceCityDistrictCodeEntity> provinceCityDistrictCodeEntities = provinceCityDistrictCodeService.queryListByName(name,code);
        return provinceCityDistrictCodeEntities;
    }

    @ApiOperation(value="获取银行编码")
    @RequestMapping(value = "/queryBanksCode",method = RequestMethod.GET)
    public List<BanksCodeEntity> queryBanksCode(String name,String code){
        List<BanksCodeEntity> banksCodeEntities = banksCodeService.queryListByName(name,code);
        return banksCodeEntities;
    }

    @ApiOperation(value="获取产品编码")
    @RequestMapping(value = "/queryProductsCode",method = RequestMethod.GET)
    public List<ProductsCodeEntity> queryProductsCode(String name,String code){
        List<ProductsCodeEntity> productsCodeEntities = productsCodeService.queryListByName(name,code);
        return productsCodeEntities;
    }


}
