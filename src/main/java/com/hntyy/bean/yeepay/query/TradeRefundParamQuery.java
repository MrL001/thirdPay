package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询退款")
public class TradeRefundParamQuery {

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户退款请求号。可包含字母、数字、下划线；需要保证在商户端不重复")
    private String refundRequestId;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "商户退款请求对应在易宝的退款单号")
    private String uniqueRefundNo;

}
