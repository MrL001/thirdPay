package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "特约商户入网 (小微) 参数")
public class RegisterSaasMicroParam {

    @ApiModelProperty(value = "入网请求号")
    private String requestNo;

    @ApiModelProperty(value = "商户编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户主体信息")
    private MicroSubjectInfo merchantSubjectInfo;

    @ApiModelProperty(value = "商户法人信息")
    private MicroCorporationInfo merchantCorporationInfo;

    @ApiModelProperty(value = "经营地址")
    private BusinessAddressInfo businessAddressInfo;

    @ApiModelProperty(value = "结算账户信息")
    private AccountInfo accountInfo;

    @ApiModelProperty(value = "1、用于接收电子签章地址，完成协议签署；2、用于接收审核已驳回状态下的原因；3、用于接收入网完成的通知。")
    private String notifyUrl;

    @ApiModelProperty(value = "开通产品信息")
    private List<ProductInfo> productInfo;

}
