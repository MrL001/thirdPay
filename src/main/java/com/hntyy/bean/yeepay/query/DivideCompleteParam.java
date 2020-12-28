package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "完结分账参数")
public class DivideCompleteParam {

    @ApiModelProperty(value = "交易发起方商编。与交易下单传入的保持一致")
    private  String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private  String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private  String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private  String uniqueOrderNo;

    @ApiModelProperty(value = "完结分账请求号，不可与分账请求号一致。在商户系统内部唯一（申请分账、完结分账应使用不同的商户分账请求号），同一分账请求号多次请求等同一次")
    private  String divideRequestId;

    @ApiModelProperty(value = "分账完结的原因描述  非必填")
    private String divideDetailDesc;

}
