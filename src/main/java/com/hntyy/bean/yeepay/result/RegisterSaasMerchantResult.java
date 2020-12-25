package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "特约商户入网&产品费率返回结果")
public class RegisterSaasMerchantResult {

    @ApiModelProperty(value = "响应编码 NIG00000：请求成功，NIG00001：必填参数有误，NIG00002：数据验证有误，NIG00003：并发请求报错，NIG99999：系统未知异常")
    private String returnCode;

    @ApiModelProperty(value = "响应描述")
    private String returnMsg;

    @ApiModelProperty(value = "入网请求号")
    private String requestNo;

    @ApiModelProperty(value = "申请单编号")
    private String applicationNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "申请状态 REVIEWING:申请审核中，REVIEW_BACK:申请已驳回，AGREEMENT_SIGNING:协议待签署 BUSINESS_OPENING:业务开通中，COMPLETED:申请已完成")
    private String applicationStatus;

    @ApiModelProperty(value = "当申请状态为“协议待签署”时，回调给商户该协议签署地址")
    private String agreementSignUrl;

    @ApiModelProperty(value = "申请已驳回”或者“申请已完成”时，回传的审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "存储来源 1.特约商户入网(企业/个体)，2.特约商户入网(小微)，3.商户产品费率变更，4.入网回调，5.产品费率变更回调")
    private int type;

}
