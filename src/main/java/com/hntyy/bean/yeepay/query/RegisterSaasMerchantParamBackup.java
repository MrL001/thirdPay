package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "特约商户入网 (企业/个体) 参数转换类")
public class RegisterSaasMerchantParamBackup extends RegisterSaasMerchantParam{

    @ApiModelProperty(value = "商户证件编号，统一社会信用代码证编号、事业单位法人证书编号、社会团体证书编号等，与商户签约类型匹配")
    private String licenceNo;

    @ApiModelProperty(value = "商户签约名称 与商户证件主体名称一致")
    private String signName;

    @ApiModelProperty(value = "商户主体信息")
    private String merchantSubjectInfoStr;

    @ApiModelProperty(value = "商户法人信息")
    private String merchantCorporationInfoStr;

    @ApiModelProperty(value = "商户联系人信息")
    private String merchantContactInfoStr;

    @ApiModelProperty(value = "经营地址")
    private String businessAddressInfoStr;

    @ApiModelProperty(value = "结算账户信息")
    private String settlementAccountInfoStr;

    @ApiModelProperty(value = "开通产品信息")
    private String productInfoStr;

    @ApiModelProperty(value = "需要变更产品的商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "存储来源 1.特约商户入网(企业/个体)，2.特约商户入网(小微)，3.商户产品费率变更")
    private int type;

}
