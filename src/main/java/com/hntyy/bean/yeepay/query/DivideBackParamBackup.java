package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "申请分账资金归还参数")
public class DivideBackParamBackup extends DivideBackParam{

    @ApiModelProperty(value = "分账详情")
    private String divideBackDetailStr;

}
