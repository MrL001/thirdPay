package com.hntyy.bean.yeepay.result;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 子商户入网资质文件上传 返回实体
 */
@Data
public class QualUploadResult {

    /**
     * 文件上传url
     */
    private String fileUrl;

    /**
     * 文件类型 1.本地文件 2.远程文件
     */
    private Integer fileType;

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
