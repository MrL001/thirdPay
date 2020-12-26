package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请退款回调地址返回结果")
public class TradeRefundNotifyResult {

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "退款申请金额")
    private String refundAmount;

    @ApiModelProperty(value = "商户退款请求号")
    private String refundRequestId;

    @ApiModelProperty(value = "易宝退款订单号")
    private String uniqueRefundNo;

    @ApiModelProperty(value = "退款请求时间")
    private String refundRequestDate;

    @ApiModelProperty(value = "SUCCESS：退款成功 FAILED:退款失败")
    private String status;

    @ApiModelProperty(value = "退款成功时间")
    private String refundSuccessDate;

    @ApiModelProperty(value = "退款失败原因")
    private String errorMessage;

}
