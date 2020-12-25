package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "开通产品信息")
public class ProductInfo {

    @ApiModelProperty(value = "产品码")
    private String productCode;

    @ApiModelProperty(value = "计费策略  SINGLE_PERCENT:单笔百分比，SINGLE_FIXED:单笔固定值，FIXED_MIX_PERCENT:单笔固定值 + 单笔百分比，PERCENT_MIX_CAP:单笔百分比 + 封顶值")
    private String rateType;

    @ApiModelProperty(value = "单笔百分比  计费策略为 SINGLE_PERCENT、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。该字段单位：%；如 0.5%，上传 0.5 即可。")
    private String percentRate;

    @ApiModelProperty(value = "单笔固定值  计费策略为 SINGLE_FIXED、FIXED_MIX_PERCENT、PERCENT_MIX_CAP 时，必填。该字段单位：元/笔；如 5 元/笔，上传 5 即可。")
    private String fixedRate;

}
