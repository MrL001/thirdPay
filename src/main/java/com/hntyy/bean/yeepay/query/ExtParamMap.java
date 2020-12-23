package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 扩展参数
 */
@Data
public class ExtParamMap {

    /**
     * 业务通道编码
     * 预留参数，无需传参
     */
    private  String specifyChannelCodes;

    /**
     * 报备费率类型
     * XIANXIA：线下；
     * XIANSHANG：线上；
     * 微信、支付宝支付时此参数必填
     */
    private  String reportFee;

}
