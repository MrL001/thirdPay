package com.hntyy.bean.yeepay.result;

import lombok.Data;

/**
 * 特约商户入网 & 产品费率 返回实体
 */
@Data
public class RegisterSaasMerchantResult {

    /**
     * 响应编码
     * NIG00000	请求成功	-
     * NIG00001	必填参数有误	-
     * NIG00002	数据验证有误	-
     * NIG00003	并发请求报错	-
     * NIG99999	系统未知异常
     */
    private String returnCode;

    /**
     * 响应描述
     */
    private String returnMsg;

    /**
     *  入网请求号
     */
    private String requestNo;

    /**
     * 申请单编号
     */
    private String applicationNo;

    private String merchantNo;

    /**
     * 申请状态
     * REVIEWING:申请审核中
     * REVIEW_BACK:申请已驳回
     * AGREEMENT_SIGNING:协议待签署 BUSINESS_OPENING:业务开通中
     * COMPLETED:申请已完成
     */
    private String applicationStatus;

}
