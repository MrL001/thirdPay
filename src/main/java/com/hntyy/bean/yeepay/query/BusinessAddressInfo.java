package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "经营地址信息")
public class BusinessAddressInfo {

    @ApiModelProperty(value = "商户实际经营地所在省编码")
    private String province;

    @ApiModelProperty(value = "商户实际经营地所在市编码")
    private String city;

    @ApiModelProperty(value = "商户实际经营地所在区编码")
    private String district;

    @ApiModelProperty(value = "商户实际经营详细地址，不需要再写省市区")
    private String address;

}
