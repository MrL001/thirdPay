package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "交易下单参数")
public class TradeOrderParam {

    @ApiModelProperty(value = "发起方商户编号")
    private String parentMerchantNo;

    @ApiModelProperty(value = "收款商户商编")
    private String merchantNo;

    @ApiModelProperty(value = "商户收款请求号")
    private String orderId;

    @ApiModelProperty(value = "订单金额。单位为元，精确到小数点后两位")
    private  String orderAmount;

    @ApiModelProperty(value = "简单描述订单信息或商品简介")
    private  String goodsName;

    @ApiModelProperty(value = "分账标识 DELAY_SETTLE：分账")
    private  String fundProcessType;

    @ApiModelProperty(value = "接收支付结果的通知地址")
    private  String notifyUrl;

    @ApiModelProperty(value = "对账备注 商户自定义参数，会展示在交易对账单中 非必填")
    private  String memo;

    @ApiModelProperty(value = "订单过期时间 格式为YYYY-MM-DD HH:mm:ss，为空时默认在请求下单120分钟后失效，最长支持30天  非必填")
    private  String expiredTime;

    @ApiModelProperty(value = "清算成功服务器回调地址")
    private  String csUrl;

}
