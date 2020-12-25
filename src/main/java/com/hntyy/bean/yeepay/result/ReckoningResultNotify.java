package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "清算成功回调数据表")
public class ReckoningResultNotify {

    @ApiModelProperty(value = "业务发起方商编")
    private String parentMerchantNo;

    @ApiModelProperty(value = "收款商户编号")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "易宝收款订单号")
    private String uniqueOrderNo;

    @ApiModelProperty(value = "状态  SUCCESS（订单清算成功）")
    private String status;

    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "清算完成时间")
    private String csSuccessDate;

    @ApiModelProperty(value = "商户手续费")
    private String merchantFee;

    @ApiModelProperty(value = "入账金额")
    private String ypSettleAmount;

}
