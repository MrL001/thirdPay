package com.hntyy.bean.yeepay.result;

import com.hntyy.bean.yeepay.query.DivideBackParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "申请分账资金归还返回结果")
public class DivideBackResult {

    @ApiModelProperty(value = "返回码")
    private String code;

    @ApiModelProperty(value = "返回信息描述")
    private String message;

    @ApiModelProperty(value = "业务方标识")
    private String bizSystemNo;

    @ApiModelProperty(value = "发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "商户分账请求号")
    private String divideRequestId;

    @ApiModelProperty(value = "商户分账资金归还请求号")
    private String divideBackRequestId;

    @ApiModelProperty(value = "易宝分账资金归还订单号")
    private String uniqueDivideBackNo;

    @ApiModelProperty(value = "分账资金归还明细")
    private String divideBackDetail;

    @ApiModelProperty(value = "PROCESSING 处理中，SUCCESS 归还成功，FAIL 归还失败")
    private String status;

}
