package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商户主体信息 (小微)")
public class MicroSubjectInfo {

    @ApiModelProperty(value = "商户签约名称")
    private String signName;

    @ApiModelProperty(value = "商户简称")
    private String shortName;

}
