package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "支付appId目录列表")
public class PayAppIdList {

    @ApiModelProperty(value = "支付appId")
    private String appId;

    @ApiModelProperty(value = "appId类型 OFFICIAL_ACCOUNT:公众号；MINI_PROGRAM:小程序")
    private String appIdType;

}
