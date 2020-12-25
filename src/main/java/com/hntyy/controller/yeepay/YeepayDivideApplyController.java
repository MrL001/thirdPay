package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSONArray;
import com.hntyy.bean.yeepay.query.*;
import com.hntyy.bean.yeepay.result.DivideApplyResult;
import com.hntyy.bean.yeepay.result.NccashierPayResult;
import com.hntyy.bean.yeepay.result.TradeOrderResult;
import com.hntyy.bean.yeepay.result.WechatConfigAddResult;
import com.hntyy.service.yeepay.*;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/yeepayDivideApply")
@Api(tags = "分账相关接口")
public class YeepayDivideApplyController {

    // sass服务商 商户编号
    private static String parentMerchantNo = "10085834246";

    // 平台商 商户编号
    private String merchantNo = "10085843316";

    @Autowired
    private WechatConfigAddService wechatConfigAddService;

    @Autowired
    private WechatConfigAddResultService wechatConfigAddResultService;

    @Autowired
    private TradeOrderParamService tradeOrderParamService;

    @Autowired
    private TradeOrderParamResultService tradeOrderParamResultService;

    @Autowired
    private NccashierPayResultService nccashierPayResultService;

    @Autowired
    private NccashierPayParamService nccashierPayParamService;

    @ApiOperation(value="申请分账")
    @RequestMapping(value = "/divideApply",method = RequestMethod.POST)
    public DivideApplyResult divideApply(@RequestBody DivideApplyParam divideApplyParam){
//        String apiUri = "/rest/v1.0/divide/apply";
//        YopRequest request = new YopRequest();
//        request.addParam("parentMerchantNo", tradeOrderParam.getParentMerchantNo());
//        request.addParam("merchantNo", tradeOrderParam.getMerchantNo());
//        request.addParam("orderId", tradeOrderParam.getOrderId());
//        request.addParam("uniqueOrderNo", tradeOrderParam.getOrderAmount());
//        request.addParam("divideRequestId", tradeOrderParam.getGoodsName());
//        request.addParam("divideDetail", tradeOrderParam.getFundProcessType());
//        try {
//            YopResponse response = YopRsaClient.post(apiUri, request);
//            Map result = (Map) response.getResult();
//            TradeOrderResult tradeOrderResult = new TradeOrderResult();
//            tradeOrderResult.setCode(result.get("code").toString());
//            tradeOrderResult.setMessage(result.get("message").toString());
//            tradeOrderResult.setBizSystemNo(result.get("bizSystemNo").toString());
//            tradeOrderResult.setParentMerchantNo(result.get("parentMerchantNo").toString());
//            tradeOrderResult.setMerchantNo(result.get("merchantNo").toString());
//            tradeOrderResult.setOrderId(result.get("orderId").toString());
//            tradeOrderResult.setUniqueOrderNo(result.get("uniqueOrderNo").toString());
//            tradeOrderResult.setToken(result.get("token").toString());
//            tradeOrderResult.setOrderAmount((BigDecimal) result.get("orderAmount"));
//            // 请求成功保存数据
//            if (!"OPR00000".equals(result.get("code"))){
//                return tradeOrderResult;
//            }
//            // 存传参
//            tradeOrderParamService.insert(tradeOrderParam);
//            // 存返回结果
//            tradeOrderParamResultService.insert(tradeOrderResult);
//            return tradeOrderResult;
//            // 处理返回值
//        } catch (Exception e) {
//            log.error("交易下单(生成预支付订单返回支付授权token)失败！param{"+tradeOrderParam+"}");
//            e.printStackTrace();
//        }
        return null;
    }


}
