package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "公众号配置参数")
public class WechatConfigAddParam {

    @ApiModelProperty(value = "发起方商户编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号 1.给平台商配置填平台商编号  2.给平台商下的子账户配置的话填子商户的商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "支付appId目录列表")
    private List<PayAppIdList> payAppIdList;

}
