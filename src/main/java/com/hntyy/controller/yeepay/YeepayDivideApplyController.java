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
    private DivideApplyParamService divideApplyParamService;

    @Autowired
    private DivideApplyResultService divideApplyResultService;

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
            divideApplyResult.setMessage(result.get("message").toString());
            divideApplyResult.setDivideRequestId(result.get("divideRequestId").toString());
            divideApplyResult.setUniqueOrderNo(result.get("uniqueOrderNo").toString());
            divideApplyResult.setDivideDetail(result.get("divideDetail").toString());
            divideApplyResult.setStatus(result.get("status").toString());
            divideApplyResult.setOrderId(result.get("orderId").toString());
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


}
