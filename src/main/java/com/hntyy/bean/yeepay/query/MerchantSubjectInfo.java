package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "商户主体信息 (企业/个体)")
public class MerchantSubjectInfo {

    @ApiModelProperty(value = "商户签约类型。 INDIVIDUAL(个体工商户)：一般为个体户、个体工商户、个体经营，ENTERPRISE(企业)：一般为有限公司、有限责任公司，" +
            "INSTITUTION(事业单位)：包括国内各级、各类政府机构、事业单位等（如：公安、党 团、司法、交通、旅游、工商税务、市政、医疗、教育、学校等机构）")
    private String signType;

    @ApiModelProperty(value = "商户证件编号，统一社会信用代码证编号、事业单位法人证书编号、社会团体证书编号等，与商户签约类型匹配")
    private String licenceNo;

    @ApiModelProperty(value = "商户证件照片 上传图片前先调用资质文件上传接口")
    private String licenceUrl;

    @ApiModelProperty(value = "商户签约名称 与商户证件主体名称一致")
    private String signName;

    @ApiModelProperty(value = "商户简称 将在收银台页面或者支付完成页向买家展示")
    private String shortName;

    @ApiModelProperty(value = "开户许可证编号 无开户许可证编号，可传基本存款账户编号")
    private String openAccountLicenceNo;

    @ApiModelProperty(value = "开户许可证照片 开户许可证，请上传基本存款账户信息表照片")
    private String openAccountLicenceUrl;

    @ApiModelProperty(value = "手持营业执照在经营场所的照片")
    private String handLicenceUrl;

}
