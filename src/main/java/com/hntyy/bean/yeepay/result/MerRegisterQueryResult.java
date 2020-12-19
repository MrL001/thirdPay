package com.hntyy.bean.yeepay.result;

import lombok.Data;

/**
 * 商户入网进度查询接口 返回实体
 */
@Data
public class MerRegisterQueryResult {

    /**
     * 响应编码
     * NIG00001	必填参数有误	-
     * NIG00002	数据验证有误	-
     * NIG99999	系统未知异常	-
     */
    private String returnCode;

    /**
     * 响应描述
     */
    private String returnMsg;

    /**
     * 入网请求号
     */
    private String requestNo;

    /**
     * 申请单编号
     */
    private String applicationNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 申请状态
     * REVIEWING(申请审核中),
     * REVIEW_BACK(申请已驳回),
     * AGREEMENT_SIGNING(协议待签署),
     * BUSINESS_OPENING(业务开通中),
     * COMPLETED(申请已完成)
     */
    private String applicationStatus;

    /**
     * 审核意见，申请已驳回时或者申请已完成时，回传的审核意见
     */
    private String auditOpinion;

}
