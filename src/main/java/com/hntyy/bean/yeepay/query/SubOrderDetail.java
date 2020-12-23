package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 子单域信息
 */
@Data
public class SubOrderDetail {

    /**
     * 商户编号
     * 收款商户编号
     */
    private String merchantNo;

    /**
     * 商户收款请求号
     * 商户系统内部单号，建议使用数字、大小写字母的组合
     */
    private String orderId;

    /**
     * 订单金额
     * 商户收款金额，数字格式最多 2 位小数
     */
    private String orderAmount;

    /**
     * 商品信息
     */
    private String goodsName;

    /**
     * 分账标识  选填
     * 不传，默认不分账
     * DELAY_SETTLE：分账
     */
    private String fundProcessType;

}
