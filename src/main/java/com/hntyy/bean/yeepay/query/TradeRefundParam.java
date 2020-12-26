package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请退款参数")
public class TradeRefundParam {

    @ApiModelProperty(value = "发起方商户编号。与交易下单传入的保持一致")
    private String parentMerchantNo;

    @ApiModelProperty(value = "收款商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "收款交易对应的商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "商户退款请求号")
    private String refundRequestId;

    @ApiModelProperty(value = "收款交易对应的易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "单位：元，两位小数，最低0.01，退款金额不能大于原订单金额，多次退款时，累计退款金额不能超过原订单金额")
    private String refundAmount;

    @ApiModelProperty(value = "退款原因")
    private String description;

    @ApiModelProperty(value = "接收退款结果通知地址")
    private String notifyUrl;

}
