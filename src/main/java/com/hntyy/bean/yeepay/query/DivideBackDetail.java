package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请分账资金归还详情")
public class DivideBackDetail {

    @ApiModelProperty(value = "分账退回金额")
    private String amount;

    @ApiModelProperty(value = "易宝分账明细单号")
    private String divideDetailNo;

    @ApiModelProperty(value = "退回原因")
    private String divideBackReason;
}
