package com.hntyy.bean.yeepay.result;

import lombok.Data;

/**
 * 子商户入网资质文件上传 返回实体
 */
@Data
public class QualUploadResult {

    /**
     * 响应信息
     */
    private String returnMsg;

    /**
     * 响应编码
     * REG00000: 请求成功
     * REG30901：文件上传异常
     */
    private String returnCode;

    /**
     * 商户资质存储url
     */
    private String merQualUrl;

}
