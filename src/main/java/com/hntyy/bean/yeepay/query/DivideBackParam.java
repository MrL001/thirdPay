package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "申请分账资金归还参数")
public class DivideBackParam {

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户分账资金归还请求号")
    private String divideBackRequestId;

    @ApiModelProperty(value = "原分账的商户请求号")
    private String divideRequestId;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "分账详情")
    private List<DivideBackDetail> divideBackDetail;


}
