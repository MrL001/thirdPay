package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "开通产品信息")
public class FeeModifyProductInfo {

    @ApiModelProperty(value = "产品码")
    private String productCode;

    @ApiModelProperty(value = "计费策略  SINGLE_PERCENT:单笔百分比，SINGLE_FIXED:单笔固定值，FIXED_MIX_PERCENT:单笔固定值 + 单笔百分比，PERCENT_MIX_CAP:单笔百分比 + 封顶值")
    private String rateType;

    @ApiModelProperty(value = "单笔百分比  计费策略为 SINGLE_PERCENT、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。该字段单位：%；如 0.5%，上传 0.5 即可。")
    private String percentRate;

    @ApiModelProperty(value = "单笔固定值  计费策略为 SINGLE_FIXED、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。该字段单位：元/笔；如 5 元/笔，上传 5 即可。")
    private String fixedRate;

    @ApiModelProperty(value = "手续费承担方  需要承担该商户交易/结算等产品手续费的商户对应的角色：如入驻商户，可上送 SETTLED_MERCHANT（入驻商商户，即号本身）、PLATFORM_MERCHANT（平台商）、SAAS_SERVICE_PROVIDER（SaaS 服务商，如有）、USER（用户）")
    private String undertaker;

    @ApiModelProperty(value = "手续费收取方式 根据商户实际业务情况上送即可，REAL_TIME（实收）、PREPAID_REAL（预付实扣）、UN_REAL_TIME（后收，部分行业商户不支持，以跟商务约定的实际情况为准）")
    private String paymentMethod;

}
