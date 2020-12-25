package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "聚合API收银台返回结果")
public class NccashierPayResult {

    @ApiModelProperty(value = "错误码")
    private  String code;

    @ApiModelProperty(value = "错误信息")
    private  String message;

    @ApiModelProperty(value = "入参回传")
    private  String payTool;

    @ApiModelProperty(value = "入参回传")
    private  String payType;

    @ApiModelProperty(value = "入参回传")
    private  String merchantNo;

    @ApiModelProperty(value = "入参回传")
    private  String token;

    @ApiModelProperty(value = "返回类型")
    private  String resultType;

    @ApiModelProperty(value = "返回数据")
    private  String resultData;

    @ApiModelProperty(value = "扩展参数")
    private  String extParamMap;

}
