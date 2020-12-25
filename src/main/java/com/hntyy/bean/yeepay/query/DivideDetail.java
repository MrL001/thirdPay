package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分账详情")
public class DivideDetail {

    @ApiModelProperty(value = "分账接收方编号(接收分账资金的易宝商编)")
    private  String ledgerNo;

    @ApiModelProperty(value = "分账金额")
    private  String amount;

    @ApiModelProperty(value = "分账说明")
    private  String divideDetailDesc;

}
