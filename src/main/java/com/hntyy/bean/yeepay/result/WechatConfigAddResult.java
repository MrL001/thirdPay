package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "公众号配置返回结果")
public class WechatConfigAddResult {

    @ApiModelProperty(value = "返回码 00001:参数错误,00002:业务规则校验错误,00011:商户状态或信息异常,00012:产品未开通,02203:配置业务异常,99999:系统异常")
    private String code;

    @ApiModelProperty(value = "返回信息描述")
    private String message;

    @ApiModelProperty(value = "受理状态  ACCEPT:受理成功,SUCCESS:配置成功,FAILED:配置失败")
    private String status;

    @ApiModelProperty(value = "配置结果  看状态主要在这个configResult中status,五分钟之后可以调用公众号配置查询接口")
    private String configResult;

}
