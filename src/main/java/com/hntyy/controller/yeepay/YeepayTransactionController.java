package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hntyy.bean.yeepay.query.*;
import com.hntyy.bean.yeepay.result.*;
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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/yeepayTransaction")
@Api(tags = "交易相关接口")
public class YeepayTransactionController {

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


    @ApiOperation(value="公众号配置(支付宝不需要配置)")
    @RequestMapping(value = "/wechatConfig",method = RequestMethod.POST)
    public WechatConfigAddResult wechatConfig(@RequestBody WechatConfigAddParam wechatConfigAddParam){
        String apiUri = "/rest/v2.0/aggpay/wechat-config/add";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", wechatConfigAddParam.getParentMerchantNo());
        request.addParam("merchantNo", wechatConfigAddParam.getMerchantNo());
        request.addParam("appIdList", JSONArray.toJSONString(wechatConfigAddParam.getPayAppIdList()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map<String,String> result = (Map) response.getResult();
            WechatConfigAddResult wechatConfigAddResult = new WechatConfigAddResult();
            wechatConfigAddResult.setCode(result.get("code"));
            wechatConfigAddResult.setMessage(result.get("message"));
            wechatConfigAddResult.setStatus(result.get("status"));
            wechatConfigAddResult.setConfigResult(result.get("configResult"));
            // 请求成功保存数据
            if (!"00000".equals(result.get("code"))){
                return wechatConfigAddResult;
            }
            // 存传参
            WechatConfigAddParamBackup wechatConfigAddParamBackup = new WechatConfigAddParamBackup();
            BeanUtils.copyProperties(wechatConfigAddParam,wechatConfigAddParamBackup);
            wechatConfigAddParamBackup.setPayAppIdListStr(wechatConfigAddParam.getPayAppIdList().toString());
            wechatConfigAddService.insert(wechatConfigAddParamBackup);
            // 存返回结果
            wechatConfigAddResultService.insert(wechatConfigAddResult);
            return wechatConfigAddResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("公众号配置失败！param{"+wechatConfigAddParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="公众号配置查询")
    @RequestMapping(value = "/wechatConfigQuery",method = RequestMethod.GET)
    public WechatConfigAddResult wechatConfigQuery(@ApiParam(value = "平台商编号",required = true)@RequestParam(name = "parentMerchantNo") String parentMerchantNo,
                                                   @ApiParam(value = "1.给平台商配置填平台商编号  2.给平台商下的子账户配置的话填子商户的商户编号",required = true)@RequestParam(name = "merchantNo") String merchantNo){
        String apiUri = "/rest/v2.0/aggpay/wechat-config/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", parentMerchantNo);
        request.addParam("merchantNo", merchantNo);
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map<String,String> result = (Map) response.getResult();
            WechatConfigAddResult wechatConfigAddResult = new WechatConfigAddResult();
            wechatConfigAddResult.setCode(result.get("code"));
            wechatConfigAddResult.setMessage(result.get("message"));
            wechatConfigAddResult.setStatus(result.get("status"));
            wechatConfigAddResult.setConfigResult(result.get("configResult"));
            return wechatConfigAddResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("公众号配置查询失败！parentMerchantNo:"+parentMerchantNo+",merchantNo:"+merchantNo);
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="交易下单(生成预支付订单返回支付授权token)")
    @RequestMapping(value = "/tradeOrder",method = RequestMethod.POST)
    public TradeOrderResult tradeOrder(@RequestBody TradeOrderParam tradeOrderParam){
        String apiUri = "/rest/v1.0/trade/order";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", tradeOrderParam.getParentMerchantNo());
        request.addParam("merchantNo", tradeOrderParam.getMerchantNo());
        request.addParam("orderId", tradeOrderParam.getOrderId());
        request.addParam("orderAmount", tradeOrderParam.getOrderAmount());
        request.addParam("goodsName", tradeOrderParam.getGoodsName());
        request.addParam("fundProcessType", tradeOrderParam.getFundProcessType());
        request.addParam("notifyUrl", tradeOrderParam.getNotifyUrl());
        request.addParam("memo", tradeOrderParam.getMemo());
        request.addParam("expiredTime", tradeOrderParam.getExpiredTime());
        request.addParam("csUrl", tradeOrderParam.getCsUrl());
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            TradeOrderResult tradeOrderResult = new TradeOrderResult();
            tradeOrderResult.setCode(result.get("code").toString());
            tradeOrderResult.setMessage(result.get("message").toString());
            tradeOrderResult.setBizSystemNo(result.get("bizSystemNo").toString());
            tradeOrderResult.setParentMerchantNo(result.get("parentMerchantNo").toString());
            tradeOrderResult.setMerchantNo(result.get("merchantNo").toString());
            tradeOrderResult.setOrderId(result.get("orderId").toString());
            tradeOrderResult.setUniqueOrderNo(result.get("uniqueOrderNo").toString());
            tradeOrderResult.setToken(result.get("token").toString());
            tradeOrderResult.setOrderAmount((BigDecimal) result.get("orderAmount"));
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return tradeOrderResult;
            }
            // 存传参
            tradeOrderParamService.insert(tradeOrderParam);
            // 存返回结果
            tradeOrderParamResultService.insert(tradeOrderResult);
            return tradeOrderResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("交易下单(生成预支付订单返回支付授权token)失败！param{"+tradeOrderParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="聚合API收银台")
    @RequestMapping(value = "/nccashierPay",method = RequestMethod.POST)
    public NccashierPayResult nccashierPay(@RequestBody NccashierPayParam nccashierPayParam){
        String apiUri = "/rest/v1.0/nccashierapi/api/pay";
        YopRequest request = new YopRequest();
        request.addParam("payTool", nccashierPayParam.getPayTool());
        request.addParam("payType", nccashierPayParam.getPayType());
        request.addParam("token", nccashierPayParam.getToken());
        request.addParam("appId", nccashierPayParam.getAppId());
        request.addParam("openId", nccashierPayParam.getOpenId());
        request.addParam("version", nccashierPayParam.getVersion());
        request.addParam("userIp", nccashierPayParam.getUserIp());
        request.addParam("extParamMap", "{\"reportFee\":\""+nccashierPayParam.getReportFee()+"\"}");
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map<String,String> result = (Map) response.getResult();
            NccashierPayResult nccashierPayResult = new NccashierPayResult();
            nccashierPayResult.setCode(result.get("code"));
            nccashierPayResult.setMessage(result.get("message"));
            nccashierPayResult.setPayTool(result.get("payTool"));
            nccashierPayResult.setPayType(result.get("payType"));
            nccashierPayResult.setMerchantNo(result.get("merchantNo"));
            nccashierPayResult.setToken(result.get("token"));
            nccashierPayResult.setResultType(result.get("resultType"));
            nccashierPayResult.setResultData(result.get("resultData"));
            nccashierPayResult.setExtParamMap(result.get("extParamMap"));
            // 请求成功保存数据
            if (!"CAS00000".equals(result.get("code"))){
                return nccashierPayResult;
            }
            // 存传参
            nccashierPayParamService.insert(nccashierPayParam);
            // 存返回结果
            nccashierPayResultService.insert(nccashierPayResult);
            return nccashierPayResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("聚合API收银台失败！param{"+nccashierPayParam+"}");
            e.printStackTrace();
        }
        return null;
    }


}
