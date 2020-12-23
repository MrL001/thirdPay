package com.hntyy.bean.yeepay.query;

import lombok.Data;

import java.util.List;

/**
 * 交易下单 返回支付授权 token
 */
@Data
public class TradeOrderQuery {

    /**
     * 发起方商户编号
     */
    private String parentMerchantNo;

    /**
     * 收款商户商编  选填 单笔必填
     */
    private String merchantNo;

    /**
     * 商户收款请求号 （单笔&合单都填）
     */
    private String orderId;

    /**
     * 订单金额 （单笔&合单都填，合单为总和）
     */
    private  String orderAmount;

    /**
     * 简单描述订单信息或商品简介 选填 单笔必填
     */
    private  String goodsName;

    /**
     * 分账标识  选填  单笔必填
     * 不传，默认不分账
     * DELAY_SETTLE：分账
     */
    private  String fundProcessType;

    /**
     * 接收支付结果的通知地址
     */
    private  String notifyUrl;

    /**
     * 对账备注。商户自定义参数，会展示在交易对账单中  选填
     */
    private  String memo;

    /**
     * 子单域信息。合单收款场景中，请在此域传入子单信息，支持传入多个子单，实现用户一笔支付多个商户商品最多支持子单条数：99   选填
     */
    private  List<SubOrderDetail> subOrderDetail;

    /**
     * 订单过期时间，格式为YYYY-MM-DD HH:mm:ss，为空时默认在请求下单120分钟后失效，最长支持30天  选填
     */
    private  String expiredTime;

    /**
     * 标准收银台的订单此地址才有作用  聚合支付无效   选填
     */
    private  String redirectUrl;

    /**
     * 清算成功服务器回调地址，不传则不通知
     */
    private  String csUrl;



}
