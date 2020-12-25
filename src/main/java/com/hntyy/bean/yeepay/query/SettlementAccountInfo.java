package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "结算账户信息  (企业/个体)")
public class SettlementAccountInfo {

    @ApiModelProperty(value = "结算方向  ACCOUNT(结算到支付账户)，BANKCARD(结算到银行账户)")
    private String settlementDirection;

    @ApiModelProperty(value = "银行账户类型  选填（结算方向为‘结算到银行账户’时必填）  UNIT_SETTLEMENT_CARD(单位结算卡)，ENTERPRISE_ACCOUNT(对公账户)，DEBIT_CARD(借记卡)，PASSBOOK(存折)")
    private String bankAccountType;

    @ApiModelProperty(value = "银行账户号码  结算方向为‘结算到银行账户’时必填")
    private String bankCardNo;

    @ApiModelProperty(value = "银行账户开户总行编码  结算方向为‘结算到银行账户’时必填")
    private String bankCode;

}
