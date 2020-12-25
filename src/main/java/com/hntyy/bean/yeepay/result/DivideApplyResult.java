package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请分账返回结果")
public class DivideApplyResult {

    @ApiModelProperty(value = "对应code的中文信息")
    private  String message;

    @ApiModelProperty(value = "商户分账请求号")
    private  String divideRequestId;

    @ApiModelProperty(value = "易宝收款订单号")
    private  String uniqueOrderNo;

    @ApiModelProperty(value = "分账详情")
    private  String divideDetail;

    @ApiModelProperty(value = "FAIL：失败，SUCCESS：成功，ACCEPT：处理中")
    private  String status;

    @ApiModelProperty(value = "商户收款请求号")
    private  String orderId;

}
