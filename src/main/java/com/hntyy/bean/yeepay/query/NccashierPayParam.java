package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "聚合API收银台参数")
public class NccashierPayParam {

    @ApiModelProperty(value = "支付工具 MINI_PROGRAM：小程序，SCCANPAY：主扫，MSCANPAY：被扫，WECHAT_OPENID：微信公众号，ZFB_SHH：支付宝生活号，EWALLET：钱包SDK，JSPAY(JS支付)")
    private  String payTool;

    @ApiModelProperty(value = "支付方式 WECHAT、ALIPAY")
    private  String payType;

    @ApiModelProperty(value = "订单系统token")
    private  String token;

    @ApiModelProperty(value = "appId")
    private  String appId;

    @ApiModelProperty(value = "用户OpenId")
    private  String openId;

    @ApiModelProperty(value = "接口版本，默认：1.0")
    private  String version;

    @ApiModelProperty(value = "用户IP地址 格式IPV4")
    private  String userIp;

    @ApiModelProperty(value = "报备费率类型 XIANXIA：线下；XIANSHANG：线上；")
    private  String reportFee;

}
