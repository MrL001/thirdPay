package com.hntyy.bean.yeepay.query;

import lombok.Data;

import java.util.List;

/**
 * 特约商户入网 (小微) 查询实体
 */
@Data
public class RegisterSaasMicroQuery {

    /**
     * 入网请求号
     */
    private String requestNo;

    /**
     * 商户编号
     */
    private String parentMerchantNo;

    /**
     * 商户主体信息
     */
    private MicroSubjectInfo merchantSubjectInfo;

    /**
     * 商户法人信息
     */
    private MicroCorporationInfo merchantCorporationInfo;

    /**
     * 经营地址
     */
    private BusinessAddressInfo businessAddressInfo;

    /**
     * 结算账户信息
     */
    private AccountInfo accountInfo;

    /**
     * 1、用于接收电子签章地址，完成协议签署；2、用于接收审核已驳回状态下的原因；3、用于接收入网完成的通知。
     */
    private String notifyUrl;

    /**
     * 开通产品信息
     */
    private List<ProductInfo> productInfo;

}
