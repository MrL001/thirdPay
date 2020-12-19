package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 商户主体信息 (小微)
 */
@Data
public class MicroSubjectInfo {

    /**
     * 商户签约名称
     */
    private String signName;

    /**
     * 商户简称
     */
    private String shortName;

}
