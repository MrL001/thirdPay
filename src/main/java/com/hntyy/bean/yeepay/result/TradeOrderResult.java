package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "交易下单返回结果")
public class TradeOrderResult {

    @ApiModelProperty(value = "返回码")
    private String code;

    @ApiModelProperty(value = "返回信息描述")
    private String message;

    @ApiModelProperty(value = "业务方")
    private String bizSystemNo;

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

}
