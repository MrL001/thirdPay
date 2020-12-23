package com.hntyy.bean.yeepay.query;

import lombok.Data;

import java.util.List;

/**
 * 公众号配置新增接口
 */
@Data
public class WechatConfigAddQuery {

    /**
     * 发起方商户编号
     */
    private String parentMerchantNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 支付授权目录列表	选填
     */
    private String tradeAuthDirList;

    /**
     * 支付appId目录列表
     */
    private List<PayAppIdList> payAppIdList;

}
