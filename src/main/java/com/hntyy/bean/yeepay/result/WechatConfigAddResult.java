package com.hntyy.bean.yeepay.result;

import lombok.Data;
import java.util.List;

/**
 * 公众号配置返回结果接口
 */
@Data
public class WechatConfigAddResult {

    /**
     * 返回码
     * 00001	参数错误	-
     * 00002	业务规则校验错误	-
     * 00011	商户状态或信息异常	-
     * 00012	产品未开通	-
     * 02203	配置业务异常	-
     * 99999	系统异常	-
     */
    private String code;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 受理状态
     * ACCEPT:受理成功
     * SUCCESS:配置成功
     * FAILED:配置失败
     */
    private String status;

    /**
     * 配置结果：五分钟之后可以调用查询。看状态主要在这个configResult中
     */
    private List<ConfigResult> configResult;

}
