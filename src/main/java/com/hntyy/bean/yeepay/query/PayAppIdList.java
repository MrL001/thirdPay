package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 支付appId目录列表
 */
@Data
public class PayAppIdList {

    /**
     * 支付appId
     */
    private String appId;

    /**
     * appId类型
     */
    private String appIdType;

    /**
     * 推荐关注appId  选填
     */
    private String subscribeAppId;

}
