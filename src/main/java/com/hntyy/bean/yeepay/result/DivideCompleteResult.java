package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "完结分账返回结果")
public class DivideCompleteResult {

    @ApiModelProperty(value = "返回码")
    private  String code;

    @ApiModelProperty(value = "返回信息")
    private  String message;

    @ApiModelProperty(value = "分账状态  FAIL：失败，SUCCESS：成功，ACCEPT：处理中")
    private  String divideStatus;

    @ApiModelProperty(value = "完结分账的金额")
    private  String amount;

}
