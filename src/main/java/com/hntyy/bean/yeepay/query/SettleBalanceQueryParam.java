package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "待结算金额查询")
public class SettleBalanceQueryParam {

    @ApiModelProperty(value = "父商户编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "子商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "PERIOD:按照传入的截止时间查询未结算可结算金额,ALL:查询全部未结算可结算金额")
    private String operatePeriod;

    @ApiModelProperty(value = "查询交易结束时间  选填")
    private String endTime;

}
