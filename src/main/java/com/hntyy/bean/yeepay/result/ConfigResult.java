package com.hntyy.bean.yeepay.result;

import lombok.Data;

import java.util.List;

/**
 * 公众号配置configResult
 */
@Data
public class ConfigResult {

    /**
     * 配置状态
     * ACCEPT:受理成功
     * SUCCESS:配置成功
     * FAILED:配置失败
     */
    private String status;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 支付appId
     */
    private String appId;

    /**
     * appId类型
     */
    private String appIdType;

}
