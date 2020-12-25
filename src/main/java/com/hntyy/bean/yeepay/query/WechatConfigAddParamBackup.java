package com.hntyy.bean.yeepay.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "公众号配置参数转换类")
public class WechatConfigAddParamBackup extends WechatConfigAddParam{

    @ApiModelProperty(value = "支付appId目录列表")
    private String payAppIdListStr;

}
