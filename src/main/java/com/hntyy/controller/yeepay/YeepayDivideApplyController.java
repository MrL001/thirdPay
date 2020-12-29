package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSONArray;
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

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/yeepayDivideApply")
@Api(tags = "分账相关接口")
public class YeepayDivideApplyController {

    @Autowired
    private DivideApplyParamService divideApplyParamService;

    @Autowired
    private DivideApplyResultService divideApplyResultService;

    @Autowired
    private DivideCompleteParamService divideCompleteParamService;

    @Autowired
    private DivideCompleteResultService divideCompleteResultService;

    @Autowired
    private DivideBackParamService divideBackParamService;

    @Autowired
    private DivideBackResultService divideBackResultService;

    @ApiOperation(value="申请分账")
    @RequestMapping(value = "/divideApply",method = RequestMethod.POST)
    public DivideApplyResult divideApply(@RequestBody DivideApplyParam divideApplyParam){
        String apiUri = "/rest/v1.0/divide/apply";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", divideApplyParam.getParentMerchantNo());
        request.addParam("merchantNo", divideApplyParam.getMerchantNo());
        request.addParam("orderId", divideApplyParam.getOrderId());
        request.addParam("uniqueOrderNo", divideApplyParam.getUniqueOrderNo());
        request.addParam("divideRequestId", divideApplyParam.getDivideRequestId());
        request.addParam("divideDetail", JSONArray.toJSONString(divideApplyParam.getDivideDetail()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            DivideApplyResult divideApplyResult = new DivideApplyResult();
            divideApplyResult.setMessage(result.get("message")!=null?result.get("message").toString():null );
            divideApplyResult.setDivideRequestId(result.get("divideRequestId")!=null?result.get("divideRequestId").toString():null);
            divideApplyResult.setUniqueOrderNo(result.get("uniqueOrderNo")!=null?result.get("uniqueOrderNo").toString():null);
            divideApplyResult.setDivideDetail(result.get("divideDetail")!=null?result.get("divideDetail").toString():null);
            divideApplyResult.setStatus(result.get("status")!=null?result.get("status").toString():null);
            divideApplyResult.setOrderId(result.get("orderId")!=null?result.get("orderId").toString():null);
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return divideApplyResult;
            }
            // 存传参
            DivideApplyParamBackup divideApplyParamBackup = new DivideApplyParamBackup();
            BeanUtils.copyProperties(divideApplyParam,divideApplyParamBackup);
            divideApplyParamBackup.setDivideDetailStr(divideApplyParam.getDivideDetail().toString());
            divideApplyParamService.insert(divideApplyParamBackup);
            // 存返回结果
            divideApplyResultService.insert(divideApplyResult);
            return divideApplyResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("申请分账失败！param：{"+divideApplyParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="查询分账结果")
    @RequestMapping(value = "/MerRegisterQuery",method = RequestMethod.POST)
    public Map MerRegisterQuery(@RequestBody DivideApplyParam divideApplyParam) {
        String apiUri = "/rest/v1.0/divide/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", divideApplyParam.getParentMerchantNo());
        request.addParam("merchantNo", divideApplyParam.getMerchantNo());
        request.addParam("divideRequestId", divideApplyParam.getDivideRequestId());
        request.addParam("orderId", divideApplyParam.getOrderId());
        request.addParam("uniqueOrderNo", divideApplyParam.getUniqueOrderNo());
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            return result;
        } catch (Exception e) {
            log.error("查询分账结果失败！param:{"+divideApplyParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="完结分账（未分完的必须调此接口钱才能入账）")
    @RequestMapping(value = "/divideComplete",method = RequestMethod.POST)
    public DivideCompleteResult divideComplete(@RequestBody DivideCompleteParam divideCompleteParam){
        String apiUri = "/rest/v1.0/divide/complete";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", divideCompleteParam.getParentMerchantNo());
        request.addParam("merchantNo", divideCompleteParam.getMerchantNo());
        request.addParam("orderId", divideCompleteParam.getOrderId());
        request.addParam("uniqueOrderNo", divideCompleteParam.getUniqueOrderNo());
        request.addParam("divideRequestId", divideCompleteParam.getDivideRequestId());
        request.addParam("divideDetailDesc", JSONArray.toJSONString(divideCompleteParam.getDivideDetailDesc()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            DivideCompleteResult divideCompleteResult = new DivideCompleteResult();
            divideCompleteResult.setMessage(result.get("message")!=null?result.get("message").toString():null);
            divideCompleteResult.setCode(result.get("code")!=null?result.get("code").toString():null);
            divideCompleteResult.setAmount(result.get("amount")!=null?result.get("amount").toString():null);
            divideCompleteResult.setDivideStatus(result.get("divideStatus")!=null?result.get("divideStatus").toString():null);
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return divideCompleteResult;
            }
            // 存传参
            divideCompleteParamService.insert(divideCompleteParam);
            // 存返回结果
            divideCompleteResultService.insert(divideCompleteResult);
            return divideCompleteResult;
            // 处理返回值
        } catch (Exception e) {
            log.error("完结分账失败！param：{"+divideCompleteParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="申请分账资金归还")
    @RequestMapping(value = "/divideBack",method = RequestMethod.POST)
    public DivideBackResult divideBack(@RequestBody DivideBackParam divideBackParam){
        String apiUri = "/rest/v1.0/divide/back";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", divideBackParam.getParentMerchantNo());
        request.addParam("merchantNo", divideBackParam.getMerchantNo());
        request.addParam("divideBackRequestId", divideBackParam.getDivideBackRequestId());
        request.addParam("divideRequestId", divideBackParam.getDivideRequestId());
        request.addParam("orderId", divideBackParam.getOrderId());
        request.addParam("uniqueOrderNo", divideBackParam.getUniqueOrderNo());
        if (divideBackParam.getDivideBackDetail() != null && divideBackParam.getDivideBackDetail().size()>0){
            request.addParam("divideBackDetail", JSONArray.toJSONString(divideBackParam.getDivideBackDetail()));
        }
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            DivideBackResult divideBackResult = new DivideBackResult();
            divideBackResult.setCode(result.get("code") != null ? result.get("code").toString():null);
            divideBackResult.setMessage(result.get("message") != null ? result.get("message").toString():null);
            divideBackResult.setBizSystemNo(result.get("bizSystemNo") != null ? result.get("bizSystemNo").toString():null);
            divideBackResult.setParentMerchantNo(result.get("parentMerchantNo") != null ? result.get("parentMerchantNo").toString():null);
            divideBackResult.setMerchantNo(result.get("merchantNo") != null ? result.get("merchantNo").toString():null);
            divideBackResult.setOrderId(result.get("orderId") != null ? result.get("orderId").toString():null);
            divideBackResult.setUniqueOrderNo(result.get("uniqueOrderNo") !=null ? result.get("uniqueOrderNo").toString():null);
            divideBackResult.setDivideRequestId(result.get("divideRequestId")!=null?result.get("divideRequestId").toString():null);
            divideBackResult.setDivideBackRequestId(result.get("divideBackRequestId")!=null?result.get("divideBackRequestId").toString():null);
            divideBackResult.setUniqueDivideBackNo(result.get("uniqueDivideBackNo")!=null?result.get("uniqueDivideBackNo").toString():null);
            divideBackResult.setDivideBackDetail(result.get("divideBackDetail")!=null?result.get("divideBackDetail").toString():null);
            divideBackResult.setStatus(result.get("status") != null ? result.get("status").toString():null);
            // 请求成功保存数据
            if (!"OPR00000".equals(result.get("code"))){
                return divideBackResult;
            }
            // 存传参
            DivideBackParamBackup divideBackParamBackup = new DivideBackParamBackup();
            BeanUtils.copyProperties(divideBackParam,divideBackParamBackup);
            divideBackParamBackup.setDivideBackDetailStr(divideBackParam.getDivideBackDetail().toString());
            divideBackParamService.insert(divideBackParamBackup);
            // 存返回结果
            divideBackResultService.insert(divideBackResult);
            return divideBackResult;
        } catch (Exception e) {
            log.error("申请分账资金归还失败！param：{"+divideBackParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="查询分账资金归还结果")
    @RequestMapping(value = "/divideBackQuery",method = RequestMethod.POST)
    public DivideBackResult divideBackQuery(@RequestBody DivideBackParamQuery divideBackParamQuery){
        String apiUri = "/rest/v1.0/divide/back/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", divideBackParamQuery.getParentMerchantNo());
        request.addParam("merchantNo", divideBackParamQuery.getMerchantNo());
        request.addParam("divideBackRequestId", divideBackParamQuery.getDivideBackRequestId());
        request.addParam("orderId", divideBackParamQuery.getOrderId());
        request.addParam("uniqueOrderNo", divideBackParamQuery.getUniqueOrderNo());
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            DivideBackResult divideBackResult = new DivideBackResult();
            divideBackResult.setCode(result.get("code") != null ? result.get("code").toString():null);
            divideBackResult.setMessage(result.get("message") != null ? result.get("message").toString():null);
            divideBackResult.setBizSystemNo(result.get("bizSystemNo") != null ? result.get("bizSystemNo").toString():null);
            divideBackResult.setParentMerchantNo(result.get("parentMerchantNo") != null ? result.get("parentMerchantNo").toString():null);
            divideBackResult.setMerchantNo(result.get("merchantNo") != null ? result.get("merchantNo").toString():null);
            divideBackResult.setOrderId(result.get("orderId") != null ? result.get("orderId").toString():null);
            divideBackResult.setUniqueOrderNo(result.get("uniqueOrderNo") !=null ? result.get("uniqueOrderNo").toString():null);
            divideBackResult.setDivideRequestId(result.get("divideRequestId")!=null?result.get("divideRequestId").toString():null);
            divideBackResult.setDivideBackRequestId(result.get("divideBackRequestId")!=null?result.get("divideBackRequestId").toString():null);
            divideBackResult.setUniqueDivideBackNo(result.get("uniqueDivideBackNo")!=null?result.get("uniqueDivideBackNo").toString():null);
            divideBackResult.setDivideBackDetail(result.get("divideBackDetail")!=null?result.get("divideBackDetail").toString():null);
            divideBackResult.setStatus(result.get("status") != null ? result.get("status").toString():null);
            return divideBackResult;
        } catch (Exception e) {
            log.error("查询分账资金归还结果失败！param：{"+divideBackParamQuery+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="待结算金额查询(当前账户可用金额)")
    @RequestMapping(value = "/settleBalanceQuery",method = RequestMethod.POST)
    public SettleBalanceQueryResult settleBalanceQuery(@RequestBody SettleBalanceQueryParam settleBalanceQueryParam) {
        String apiUri = "/rest/v1.0/settle/balance/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", settleBalanceQueryParam.getParentMerchantNo());
        request.addParam("merchantNo", settleBalanceQueryParam.getMerchantNo());
        request.addParam("operatePeriod", settleBalanceQueryParam.getOperatePeriod());
        request.addParam("endTime", settleBalanceQueryParam.getEndTime());
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            SettleBalanceQueryResult settleBalanceQueryResult = new SettleBalanceQueryResult();
            settleBalanceQueryResult.setCode(result.get("code") != null ? result.get("code").toString():null);
            settleBalanceQueryResult.setMessage(result.get("message") != null ? result.get("message").toString():null);
            settleBalanceQueryResult.setParentMerchantNo(result.get("parentMerchantNo") != null ? result.get("parentMerchantNo").toString():null);
            settleBalanceQueryResult.setMerchantNo(result.get("merchantNo") != null ? result.get("merchantNo").toString():null);
            settleBalanceQueryResult.setSettlableAmount(result.get("settlableAmount") != null ? result.get("settlableAmount").toString():null);
            settleBalanceQueryResult.setUnsettledAmount(result.get("unsettledAmount") != null ? result.get("unsettledAmount").toString():null);
            return settleBalanceQueryResult;
        } catch (Exception e) {
            log.error("查询退款结果失败！param:{"+settleBalanceQueryParam+"}");
            e.printStackTrace();
        }
        return null;
    }

}
