package com.hntyy.bean.yeepay.query;

import lombok.Data;

/**
 * 结算账户信息  (小微)
 */
@Data
public class AccountInfo {

    /**
     * 银行账户类型
     */
    private String bankAccountType;

    /**
     * 银行账户号码
     */
    private String bankCardNo;

    /**
     * 银行账户开户总行编码
     */
    private String bankCode;

}
