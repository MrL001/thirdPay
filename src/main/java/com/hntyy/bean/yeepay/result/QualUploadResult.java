package com.hntyy.bean.yeepay.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "资质文件上传返回结果")
public class QualUploadResult {

    @ApiModelProperty(value = "文件上传url")
    private String fileUrl;

    @ApiModelProperty(value = "文件类型 1.本地文件 2.远程文件")
    private Integer fileType;

    @ApiModelProperty(value = "响应信息")
    private String returnMsg;

    @ApiModelProperty(value = "响应编码  REG00000: 请求成功,REG30901：文件上传异常")
    private String returnCode;

    @ApiModelProperty(value = "商户资质存储url")
    private String merQualUrl;

}
