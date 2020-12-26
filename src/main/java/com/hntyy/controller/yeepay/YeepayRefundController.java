package com.hntyy.controller.yeepay;

import com.hntyy.bean.yeepay.query.*;
import com.hntyy.bean.yeepay.result.TradeRefundResult;
import com.hntyy.service.yeepay.*;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/yeepayRefund")
@Api(tags = "退款相关接口")
public class YeepayRefundController {

    @Autowired
    private TradeRefundParamService divideApplyParamService;

    @Autowired
    private TradeRefundResultService divideApplyResultService;

    @ApiOperation(value="申请退款")
    @RequestMapping(value = "/tradeRefund",method = RequestMethod.POST)
    public TradeRefundResult tradeRefund(@RequestBody TradeRefundParam tradeRefundParam){
        String apiUri = "/rest/v1.0/trade/refund";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", tradeRefundParam.getParentMerchantNo());
        request.addParam("merchantNo", tradeRefundParam.getMerchantNo());
        request.addParam("orderId", tradeRefundParam.getOrderId());
        request.addParam("refundRequestId", tradeRefundParam.getRefundRequestId());
        request.addParam("uniqueOrderNo", tradeRefundParam.getUniqueOrderNo());
        request.addParam("refundAmount", tradeRefundParam.getRefundAmount());
        request.addParam("description", tradeRefundParam.getDescription());
        request.addParam("notifyUrl", tradeRefundParam.getNotifyUrl());
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            TradeRefundResult tradeRefundResult = new TradeRefundResult();
            tradeRefundResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            tradeRefundResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            tradeRefundResult.setParentMerchantNo(result.get("parentMerchantNo")!=null?result.get("parentMerchantNo").toString():null);
            tradeRefundResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            tradeRefundResult.setOrderId(result.get("orderId")!=null?result.get("orderId").toString():null);
            tradeRefundResult.setRefundRequestId(result.get("refundRequestId")!=null?result.get("refundRequestId").toString():null);
            tradeRefundResult.setUniqueRefundNo(result.get("uniqueRefundNo")!=null?result.get("uniqueRefundNo").toString():null);
            tradeRefundResult.setStatus(result.get("status")!=null?result.get("status").toString():null);
            tradeRefundResult.setRefundAmount(result.get("refundAmount")!=null?result.get("refundAmount").toString():null);
            tradeRefundResult.setRefundRequestDate(result.get("refundRequestDate")!=null?result.get("refundRequestDate").toString():null);
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return tradeRefundResult;
            }
            // 存传参
            divideApplyParamService.insert(tradeRefundParam);
            // 存返回结果
            divideApplyResultService.insert(tradeRefundResult);
            return tradeRefundResult;
        } catch (Exception e) {
            log.error("申请退款失败！param：{"+tradeRefundParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="查询退款")
    @RequestMapping(value = "/refundQuery",method = RequestMethod.POST)
    public Map refundQuery(@RequestBody TradeRefundParamQuery tradeRefundParamQuery) {
        String apiUri = "/rest/v1.0/trade/refund/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", tradeRefundParamQuery.getParentMerchantNo());
        request.addParam("merchantNo", tradeRefundParamQuery.getMerchantNo());
        request.addParam("refundRequestId", tradeRefundParamQuery.getRefundRequestId());
        request.addParam("orderId", tradeRefundParamQuery.getOrderId());
        request.addParam("uniqueRefundNo", tradeRefundParamQuery.getUniqueRefundNo());
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            return result;
        } catch (Exception e) {
            log.error("查询退款结果失败！param:{"+tradeRefundParamQuery+"}");
            e.printStackTrace();
        }
        return null;
    }

}
