package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "特约商户入网 (企业/个体) 参数")
public class RegisterSaasMerchantParam {

    @ApiModelProperty(value = "入网请求号")
    private String requestNo;

    @ApiModelProperty(value = "商户编号 平台商入网传SAAS服务商编号：10085834246，入驻商户入网传平台商编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "入网商户业务角色  ORDINARY_MERCHANT:标准商户,PLATFORM_MERCHANT:平台商,SETTLED_MERCHANT:入驻商户")
    private String businessRole;

    @ApiModelProperty(value = "商户主体信息")
    private MerchantSubjectInfo merchantSubjectInfo;

    @ApiModelProperty(value = "商户法人信息")
    private MerchantCorporationInfo merchantCorporationInfo;

    @ApiModelProperty(value = "商户联系人信息")
    private MerchantContactInfo merchantContactInfo;

    @ApiModelProperty(value = "经营地址")
    private BusinessAddressInfo businessAddressInfo;

    @ApiModelProperty(value = "结算账户信息")
    private SettlementAccountInfo settlementAccountInfo;

    @ApiModelProperty(value = "1、用于接收电子签章地址，完成协议签署；2、用于接收审核已驳回状态下的原因；3、用于接收入网完成的通知。")
    private String notifyUrl;

    @ApiModelProperty(value = "开通产品信息")
    private List<ProductInfo> productInfo;

}
