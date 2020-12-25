package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "商户产品费率变更参数")
public class MerProductFeeModifyParam {

    @ApiModelProperty(value = "入网请求号")
    private String requestNo;

    @ApiModelProperty(value = "如果为平台商拓展商户（入驻商户），此为平台商商户编号；如为saas服务商拓展商户（平台商/标准商户），此为saas服务商商户编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "需要变更产品的商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "1、用于接收电子签章地址，完成协议签署；2、用于接收审核已驳回状态下的原因；3、用于接收入网完成的通知。")
    private String notifyUrl;

    @ApiModelProperty(value = "开通产品信息")
    private List<FeeModifyProductInfo> productInfo;

}
