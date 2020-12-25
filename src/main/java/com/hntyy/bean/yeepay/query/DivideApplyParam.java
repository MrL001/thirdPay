package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "申请分账参数")
public class DivideApplyParam {

    @ApiModelProperty(value = "交易发起方商编。与交易下单传入的保持一致")
    private  String parentMerchantNo;

    @ApiModelProperty(value = "收款商户编号")
    private  String merchantNo;

    @ApiModelProperty(value = "商户请求收款的交易单号")
    private  String orderId;

    @ApiModelProperty(value = "收款交易对应在易宝的收款单号")
    private  String uniqueOrderNo;

    @ApiModelProperty(value = "商户分账请求号")
    private  String divideRequestId;

    @ApiModelProperty(value = "支付方式 WECHAT、ALIPAY")
    private List<DivideDetail> divideDetail;

}
