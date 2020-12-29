package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请求地址")
public class NotifyUrlEntity {

    @ApiModelProperty(value = "本地url")
    private  String localUrl;

    @ApiModelProperty(value = "传参url")
    private  String paramUrl;

    @ApiModelProperty(value = "回调类型。1.特约商户入网(企业/个体)，2.商户产品费率变更，3.支付结果，4.清算成功，5.申请退款")
    private  Integer type;

    @ApiModelProperty(value = "请求号id，标志唯一性")
    private  String requestId;

}
