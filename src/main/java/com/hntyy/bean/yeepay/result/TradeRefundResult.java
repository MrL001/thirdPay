package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请退款返回结果")
public class TradeRefundResult {

    @ApiModelProperty(value = "结果码，OPR00000表示成功")
    private String code;

    @ApiModelProperty(value = "信息描述，对应code的中文信息")
    private String message;

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "商户退款请求号")
    private String refundRequestId;

    @ApiModelProperty(value = "商户退款请求对应在易宝的退款单号")
    private String uniqueRefundNo;

    @ApiModelProperty(value = "PROCESSING：处理中，SUCCESS：退款成功，FAILED：退款失败，REJECTIVE：退款拒绝")
    private String status;

    @ApiModelProperty(value = "退款申请金额")
    private String refundAmount;

    @ApiModelProperty(value = "退款请求时间")
    private String refundRequestDate;

}
