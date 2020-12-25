package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商户联系人信息  (企业/个体)")
public class MerchantContactInfo {

    @ApiModelProperty(value = "商户联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "商户联系人证件号码")
    private String contactLicenceNo;

    @ApiModelProperty(value = "商户联系人手机号")
    private String contactMobile;

    @ApiModelProperty(value = "商户联系人邮箱")
    private String contactEmail;

}
