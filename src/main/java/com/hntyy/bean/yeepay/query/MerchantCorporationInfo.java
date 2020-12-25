package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商户法人信息 (企业/个体)")
public class MerchantCorporationInfo {

    @ApiModelProperty(value = "法人姓名 经营者/法人对应身份证件的姓名")
    private String legalName;

    @ApiModelProperty(value = "法人证件类型 ID_CARD(法人身份证)，PASSPORT(护照)，HMT_VISITORPASS(港澳台居民往来内地通行证)，SOLDIER(士兵证)，" +
            "OFFICERS(军官证)，OVERSEAS_CARD(境外证件)")
    private String legalLicenceType;

    @ApiModelProperty(value = "法人证件号码")
    private String legalLicenceNo;

    @ApiModelProperty(value = "法人证件正面照片")
    private String legalLicenceFrontUrl;

    @ApiModelProperty(value = "法人证件反面照片")
    private String legalLicenceBackUrl;

}
