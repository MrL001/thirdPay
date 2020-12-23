package com.hntyy.bean.yeepay.query;

import lombok.Data;

import java.util.List;

/**
 * 聚合API收银台
 */
@Data
public class NccashierPayQuery {

    /**
     * 支付工具：
     * SCCANPAY：主扫
     * MSCANPAY：被扫
     * WECHAT_OPENID：微信公众号
     * ZFB_SHH：支付宝生活号
     * EWALLET：钱包SDK
     * MINI_PROGRAM：小程序
     * JSPAY(JS支付)
     */
    private  String payTool;

    /**
     * 支付方式：
     * payTool为SCCANPAY、MSCANPAY时可选:WECHAT、ALIPAY、UPOP
     * payTool为WECHAT_OPENID时可选:WECHAT
     * payTool为MINI_PROGRAM时可选:WECHAT、ALIPAY
     * payTool为ZFB_SHH时可选:ALIPAY
     * payTool为EWALLET时可选:UPOP
     * payTool为JSPAY时可选:UPOP
     */
    private  String payType;

    /**
     * 订单系统token
     */
    private  String token;

    /**
     * 公众号ID  选填
     * payTool为WECHAT_OPENID、MINI_PROGRAM时必填
     */
    private  String appId;

    /**
     * 用户OpenId  选填
     * payTool为WECHAT_OPENID、MINI_PROGRAM、ZFB_SHH时必填
     */
    private  String openId;

    /**
     * 接口版本：
     * 接口版本，默认：1.0
     */
    private  String version;

    /**
     * 授权码  选填
     * payTool为MSCANPAY时必填
     */
    private  String payEmpowerNo;

    /**
     * 设备号  选填
     * payTool为MSCANPAY时必填
     */
    private  String merchantTerminalId;

    /**
     * 门店编码  选填
     * payTool为MSCANPAY时必填
     */
    private  String merchantStoreNo;

    /**
     * 用户IP	格式IPV4格式
     */
    private  String userIp;

    /**
     * 扩展参数  选填
     * Json格式 微信和支付宝支付时必传[报备费率类型]
     */
    private List<ExtParamMap> extParamMap;

    /**
     * 扩展参数  选填
     * 预留字段，无需传参
     */
    private  String userNo;

    /**
     * 用户类型  选填
     * 预留字段，无需传参
     */
    private  String userType;

}
