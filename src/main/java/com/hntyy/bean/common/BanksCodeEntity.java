package com.hntyy.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "银行编码")
public class BanksCodeEntity {

    private Integer id;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行简称")
    private String bankShortname;

    @ApiModelProperty(value = "银行编码")
    private String bankCode;

}
