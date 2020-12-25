package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "结算账户信息  (小微)")
public class AccountInfo {

    @ApiModelProperty(value = "银行账户类型")
    private String bankAccountType;

    @ApiModelProperty(value = "银行账户号码")
    private String bankCardNo;

    @ApiModelProperty(value = "银行账户开户总行编码")
    private String bankCode;

}
