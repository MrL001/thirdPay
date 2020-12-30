package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hntyy.bean.yeepay.query.*;
import com.hntyy.bean.yeepay.result.*;
import com.hntyy.enums.NotifyUrlEnum;
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

    @Autowired
    private NotifyUrlService notifyUrlService;


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
            Map result = (Map) response.getResult();
            WechatConfigAddResult wechatConfigAddResult = new WechatConfigAddResult();
            wechatConfigAddResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            wechatConfigAddResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            wechatConfigAddResult.setStatus(result.get("status")!=null?result.get("status").toString():null);
            wechatConfigAddResult.setConfigResult(result.get("configResult")!=null?result.get("configResult").toString():null);
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
            Map result = (Map) response.getResult();
            WechatConfigAddResult wechatConfigAddResult = new WechatConfigAddResult();
            wechatConfigAddResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            wechatConfigAddResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            wechatConfigAddResult.setStatus(result.get("status")!=null?result.get("status").toString():null);
            wechatConfigAddResult.setConfigResult(result.get("configResult")!=null?result.get("configResult").toString():null);
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
    public TradeOrderResult tradeOrder(@ModelAttribute TradeOrderParam tradeOrderParam){
        String apiUri = "/rest/v1.0/trade/order";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", tradeOrderParam.getParentMerchantNo());
        request.addParam("merchantNo", tradeOrderParam.getMerchantNo());
        request.addParam("orderId", tradeOrderParam.getOrderId());
        request.addParam("orderAmount", tradeOrderParam.getOrderAmount());
        request.addParam("goodsName", tradeOrderParam.getGoodsName());
        request.addParam("fundProcessType", tradeOrderParam.getFundProcessType());
        request.addParam("notifyUrl", NotifyUrlEnum.ZFJG.getValue());
        request.addParam("memo", tradeOrderParam.getMemo());
        request.addParam("expiredTime", tradeOrderParam.getExpiredTime());
        request.addParam("csUrl", NotifyUrlEnum.QSCG.getValue());
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            TradeOrderResult tradeOrderResult = new TradeOrderResult();
            tradeOrderResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            tradeOrderResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            tradeOrderResult.setBizSystemNo(result.get("bizSystemNo")!=null?result.get("bizSystemNo").toString():null);
            tradeOrderResult.setParentMerchantNo(result.get("parentMerchantNo")!=null?result.get("parentMerchantNo").toString():null);
            tradeOrderResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            tradeOrderResult.setOrderId(result.get("orderId")!=null?result.get("orderId").toString():null);
            tradeOrderResult.setUniqueOrderNo(result.get("uniqueOrderNo")!=null?result.get("uniqueOrderNo").toString():null);
            tradeOrderResult.setToken(result.get("token")!=null?result.get("token").toString():null);
            tradeOrderResult.setOrderAmount(result.get("orderAmount")!=null?(BigDecimal)result.get("orderAmount"):null);
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return tradeOrderResult;
            }
            // 存地址
            NotifyUrlEntity notifyUrlEntity = new NotifyUrlEntity();
            notifyUrlEntity.setLocalUrl(NotifyUrlEnum.ZFJG.getValue());
            notifyUrlEntity.setParamUrl(tradeOrderParam.getNotifyUrl() != null && !"".equals(tradeOrderParam.getNotifyUrl()) ? tradeOrderParam.getNotifyUrl():null);
            notifyUrlEntity.setRequestId(tradeOrderParam.getOrderId() != null && !"".equals(tradeOrderParam.getOrderId()) ? tradeOrderParam.getOrderId():null);
            notifyUrlEntity.setType(NotifyUrlEnum.ZFJG.getKey());
            notifyUrlService.insert(notifyUrlEntity);
            NotifyUrlEntity notifyUrlEntity2 = new NotifyUrlEntity();
            notifyUrlEntity2.setLocalUrl(NotifyUrlEnum.QSCG.getValue());
            notifyUrlEntity2.setParamUrl(tradeOrderParam.getCsUrl() != null && !"".equals(tradeOrderParam.getCsUrl()) ? tradeOrderParam.getCsUrl():null);
            notifyUrlEntity2.setRequestId(tradeOrderParam.getOrderId() != null && !"".equals(tradeOrderParam.getOrderId()) ? tradeOrderParam.getOrderId():null);
            notifyUrlEntity2.setType(NotifyUrlEnum.QSCG.getKey());
            notifyUrlService.insert(notifyUrlEntity2);
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
            Map result = (Map) response.getResult();
            NccashierPayResult nccashierPayResult = new NccashierPayResult();
            nccashierPayResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            nccashierPayResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            nccashierPayResult.setPayTool(result.get("payTool")!=null?result.get("payTool").toString():null);
            nccashierPayResult.setPayType(result.get("payType")!=null?result.get("payType").toString():null);
            nccashierPayResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            nccashierPayResult.setToken(result.get("token")!=null?result.get("token").toString():null);
            nccashierPayResult.setResultType(result.get("resultType")!=null?result.get("resultType").toString():null);
            nccashierPayResult.setResultData(result.get("resultData")!=null?result.get("resultData").toString():null);
            nccashierPayResult.setExtParamMap(result.get("extParamMap")!=null?result.get("extParamMap").toString():null);
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
