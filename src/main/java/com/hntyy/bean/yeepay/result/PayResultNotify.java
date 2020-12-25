package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "支付结果通知回调数据表")
public class PayResultNotify {

    @ApiModelProperty(value = "交易下单传入的商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "支付成功时间")
    private String paySuccessDate;

    @ApiModelProperty(value = "渠道类型 WECHAT：微信，ALIPAY：支付宝，UNIONPAY：银联，APPLEPAY：苹果支付")
    private String channel;

    @ApiModelProperty(value = "支付方式 MINI_PROGRAM：小程序支付")
    private String payWay;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "付款信息")
    private String payerInfo;

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "订单状态")
    private String status;
}
