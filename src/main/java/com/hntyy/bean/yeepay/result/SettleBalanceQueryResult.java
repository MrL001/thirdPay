package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "待结算金额查询返回结果")
public class SettleBalanceQueryResult {

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "父商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "子商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户可结算金额")
    private String settlableAmount;

    @ApiModelProperty(value = "未结算金额")
    private String unsettledAmount;

}
