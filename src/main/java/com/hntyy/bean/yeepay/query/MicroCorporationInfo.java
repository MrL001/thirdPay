package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 商户法人信息 (小微)
 */
@Data
public class MicroCorporationInfo {

    /**
     * 法人证件类型 (选填)
     * ID_CARD(法人身份证)
     * PASSPORT(护照)
     * HMT_VISITORPASS(港澳台居民往来内地通行证)
     * SOLDIER(士兵证)
     * OFFICERS(军官证)
     * OVERSEAS_CARD(境外证件)
     * 不填默认法人身份证
     */
    private String legalLicenceType;

    /**
     * 法人证件号码
     */
    private String legalLicenceNo;

    /**
     * 法人身份证件正面照片
     */
    private String legalLicenceFrontUrl;

    /**
     * 法人身份证件反面照片
     */
    private String legalLicenceBackUrl;

    /**
     * 法人手机号
     */
    private String mobile;

}
