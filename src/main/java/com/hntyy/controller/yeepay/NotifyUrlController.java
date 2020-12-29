package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hntyy.bean.yeepay.query.NotifyUrlEntity;
import com.hntyy.bean.yeepay.result.PayResultNotify;
import com.hntyy.bean.yeepay.result.ReckoningResultNotify;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import com.hntyy.bean.yeepay.result.TradeRefundNotifyResult;
import com.hntyy.enums.NotifyUrlEnum;
import com.hntyy.service.yeepay.*;
import com.yeepay.g3.sdk.yop.encrypt.CertTypeEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigitalEnvelopeDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;

@Slf4j
@RestController
@RequestMapping("/notifyUrl")
@Api(tags = "处理回调地址")
public class NotifyUrlController {

    @Autowired
    private RegisterSaasMerchantResultService registerSaasMerchantResultService;

    @Autowired
    private PayResultNotifyService payResultNotifyService;

    @Autowired
    private ReckoningResultNotifyService reckoningResultNotifyService;

    @Autowired
    private TradeRefundNotifyResultService tradeRefundNotifyResultService;

    @Autowired
    private NotifyUrlService notifyUrlService;

    @ApiOperation(value="特约商户入网 回调地址")
    @RequestMapping(value = "/registerSaasMerchant",method = RequestMethod.POST)
    public String registerSaasMerchant(HttpServletRequest request){
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        //设置商户私钥
        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
        //设置易宝公钥
        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
        //解密验签
        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
        String plainText = dto.getPlainText();
        try {
            JSONObject jsonObject = JSON.parseObject(plainText);
            RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
            registerSaasMerchantResult.setRequestNo(jsonObject.getString("requestNo") != null ? jsonObject.getString("requestNo"):null);
            registerSaasMerchantResult.setApplicationNo(jsonObject.getString("applicationNo") != null ? jsonObject.getString("applicationNo"):null);
            registerSaasMerchantResult.setMerchantNo(jsonObject.getString("merchantNo") != null ? jsonObject.getString("merchantNo"):null);
            registerSaasMerchantResult.setApplicationStatus(jsonObject.getString("applicationStatus") != null ? jsonObject.getString("applicationStatus"):null);
            registerSaasMerchantResult.setAgreementSignUrl(jsonObject.getString("agreementSignUrl") != null ? jsonObject.getString("agreementSignUrl"):null);
            registerSaasMerchantResult.setAuditOpinion(jsonObject.getString("auditOpinion") != null ? jsonObject.getString("auditOpinion"):null);
            registerSaasMerchantResult.setType(4);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
            // 取最新地址回调
            NotifyUrlEntity notifyUrl = notifyUrlService.getNotifyUrl(registerSaasMerchantResult.getRequestNo(), NotifyUrlEnum.TYSHRW.getKey());
            if (notifyUrl != null){
                String paramUrl = notifyUrl.getParamUrl();
                // 做处理
                if (paramUrl != null && !"".equals(paramUrl)){
                    // ...............
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            log.error("特约商户入网(企业/个体) 回调报错：param:{"+plainText+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="商户产品费率变更 回调地址")
    @RequestMapping(value = "/merProductFeeModify",method = RequestMethod.POST)
    public String merProductFeeModify(HttpServletRequest request){
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        //设置商户私钥
        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
        //设置易宝公钥
        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
        //解密验签
        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
        String plainText = dto.getPlainText();
        try {
            JSONObject jsonObject = JSON.parseObject(plainText);
            RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
            registerSaasMerchantResult.setRequestNo(jsonObject.getString("requestNo") != null ? jsonObject.getString("requestNo"):null);
            registerSaasMerchantResult.setApplicationNo(jsonObject.getString("applicationNo") != null ? jsonObject.getString("applicationNo"):null);
            registerSaasMerchantResult.setMerchantNo(jsonObject.getString("merchantNo") != null ? jsonObject.getString("merchantNo"):null);
            registerSaasMerchantResult.setApplicationStatus(jsonObject.getString("applicationStatus") != null ? jsonObject.getString("applicationStatus"):null);
            registerSaasMerchantResult.setAgreementSignUrl(jsonObject.getString("agreementSignUrl") != null ? jsonObject.getString("agreementSignUrl"):null);
            registerSaasMerchantResult.setAuditOpinion(jsonObject.getString("auditOpinion") != null ? jsonObject.getString("auditOpinion"):null);
            registerSaasMerchantResult.setType(5);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
            // 取最新地址回调
            NotifyUrlEntity notifyUrl = notifyUrlService.getNotifyUrl(registerSaasMerchantResult.getRequestNo(), NotifyUrlEnum.SHCPFLBG.getKey());
            if (notifyUrl != null){
                String paramUrl = notifyUrl.getParamUrl();
                // 做处理
                if (paramUrl != null && !"".equals(paramUrl)){
                    // ...............
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            log.error("商户产品费率变更 回调报错：param:{"+plainText+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="支付结果 回调地址")
    @RequestMapping(value = "/payResult",method = RequestMethod.POST)
    public String payResult(HttpServletRequest request){
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
        String plainText = dto.getPlainText();
        try {
            JSONObject jsonObject = JSON.parseObject(plainText);
            PayResultNotify payResultNotify = new PayResultNotify();
            payResultNotify.setOrderId(jsonObject.getString("orderId") != null ? jsonObject.getString("orderId"):null);
            payResultNotify.setPaySuccessDate(jsonObject.getString("paySuccessDate") != null ? jsonObject.getString("paySuccessDate"):null);
            payResultNotify.setChannel(jsonObject.getString("channel") != null ? jsonObject.getString("channel"):null);
            payResultNotify.setPayWay(jsonObject.getString("payWay") != null ? jsonObject.getString("payWay"):null);
            payResultNotify.setUniqueOrderNo(jsonObject.getString("uniqueOrderNo") != null ? jsonObject.getString("uniqueOrderNo"):null);
            payResultNotify.setOrderAmount(jsonObject.getString("orderAmount") != null ? jsonObject.getBigDecimal("orderAmount"):null);
            payResultNotify.setPayAmount(jsonObject.getString("payAmount") != null ? new BigDecimal(jsonObject.getString("payAmount")):null);
            payResultNotify.setPayerInfo(jsonObject.getString("payerInfo") != null ? jsonObject.getString("payerInfo"):null);
            payResultNotify.setParentMerchantNo(jsonObject.getString("parentMerchantNo") != null ? jsonObject.getString("parentMerchantNo"):null);
            payResultNotify.setMerchantNo(jsonObject.getString("merchantNo") != null ? jsonObject.getString("merchantNo"):null);
            payResultNotify.setStatus(jsonObject.getString("status") != null ? jsonObject.getString("status"):null);
            payResultNotifyService.insert(payResultNotify);
            // 取最新地址回调
            NotifyUrlEntity notifyUrl = notifyUrlService.getNotifyUrl(payResultNotify.getOrderId(), NotifyUrlEnum.ZFJG.getKey());
            if (notifyUrl != null){
                String paramUrl = notifyUrl.getParamUrl();
                // 做处理
                if (paramUrl != null && !"".equals(paramUrl)){
                    // ...............
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            log.error("支付结果 回调报错：param:{"+plainText+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="清算成功 回调地址")
    @RequestMapping(value = "/reckoningSuccess",method = RequestMethod.POST)
    public String reckoningSuccess(HttpServletRequest request){
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        //设置商户私钥
        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
        //设置易宝公钥
        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
        //解密验签
        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
        String plainText = dto.getPlainText();
        try {
            JSONObject jsonObject = JSON.parseObject(plainText);
            ReckoningResultNotify reckoningResultNotify = new ReckoningResultNotify();
            reckoningResultNotify.setParentMerchantNo(jsonObject.getString("parentMerchantNo") != null ? jsonObject.getString("parentMerchantNo"):null);
            reckoningResultNotify.setMerchantNo(jsonObject.getString("merchantNo") != null ? jsonObject.getString("merchantNo"):null);
            reckoningResultNotify.setOrderId(jsonObject.getString("orderId") != null ? jsonObject.getString("orderId"):null);
            reckoningResultNotify.setUniqueOrderNo(jsonObject.getString("uniqueOrderNo") != null ? jsonObject.getString("uniqueOrderNo"):null);
            reckoningResultNotify.setStatus(jsonObject.getString("status") != null ? jsonObject.getString("status"):null);
            reckoningResultNotify.setOrderAmount(jsonObject.getString("orderAmount") != null ? jsonObject.getString("orderAmount"):null);
            reckoningResultNotify.setCsSuccessDate(jsonObject.getString("csSuccessDate") != null ? jsonObject.getString("csSuccessDate"):null);
            reckoningResultNotify.setMerchantFee(jsonObject.getString("merchantFee") != null ? jsonObject.getString("merchantFee"):null);
            reckoningResultNotify.setYpSettleAmount(jsonObject.getString("ypSettleAmount") != null ? jsonObject.getString("ypSettleAmount"):null);
            reckoningResultNotifyService.insert(reckoningResultNotify);
            // 取最新地址回调
            NotifyUrlEntity notifyUrl = notifyUrlService.getNotifyUrl(reckoningResultNotify.getOrderId(), NotifyUrlEnum.QSCG.getKey());
            if (notifyUrl != null){
                String paramUrl = notifyUrl.getParamUrl();
                // 做处理
                if (paramUrl != null && !"".equals(paramUrl)){
                    // ...............
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            log.error("清算成功 回调报错：param:{"+plainText+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="申请退款 回调地址")
    @RequestMapping(value = "/tradeRefund",method = RequestMethod.POST)
    public String tradeRefund(HttpServletRequest request){
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        //设置商户私钥
        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
        //设置易宝公钥
        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
        //解密验签
        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
        String plainText = dto.getPlainText();
        try {
            JSONObject jsonObject = JSON.parseObject(plainText);
            TradeRefundNotifyResult tradeRefundNotifyResult = new TradeRefundNotifyResult();
            tradeRefundNotifyResult.setParentMerchantNo(jsonObject.getString("parentMerchantNo") != null ? jsonObject.getString("parentMerchantNo"):null);
            tradeRefundNotifyResult.setMerchantNo(jsonObject.getString("merchantNo") != null ? jsonObject.getString("merchantNo"):null);
            tradeRefundNotifyResult.setOrderId(jsonObject.getString("orderId") != null ? jsonObject.getString("orderId"):null);
            tradeRefundNotifyResult.setUniqueOrderNo(jsonObject.getString("uniqueOrderNo") != null ? jsonObject.getString("uniqueOrderNo"):null);
            tradeRefundNotifyResult.setRefundAmount(jsonObject.getString("refundAmount") != null ? jsonObject.getString("refundAmount"):null);
            tradeRefundNotifyResult.setUniqueRefundNo(jsonObject.getString("uniqueRefundNo") != null ? jsonObject.getString("uniqueRefundNo"):null);
            tradeRefundNotifyResult.setRefundRequestDate(jsonObject.getString("refundRequestDate") != null ? jsonObject.getString("refundRequestDate"):null);
            tradeRefundNotifyResult.setStatus(jsonObject.getString("status") != null ? jsonObject.getString("status"):null);
            tradeRefundNotifyResult.setRefundSuccessDate(jsonObject.getString("refundSuccessDate") != null ? jsonObject.getString("refundSuccessDate"):null);
            tradeRefundNotifyResult.setErrorMessage(jsonObject.getString("errorMessage") != null ? jsonObject.getString("errorMessage"):null);
            tradeRefundNotifyResultService.insert(tradeRefundNotifyResult);
            // 取最新地址回调
            NotifyUrlEntity notifyUrl = notifyUrlService.getNotifyUrl(tradeRefundNotifyResult.getOrderId(), NotifyUrlEnum.SQTK.getKey());
            if (notifyUrl != null){
                String paramUrl = notifyUrl.getParamUrl();
                // 做处理
                if (paramUrl != null && !"".equals(paramUrl)){
                    // ...............
                }
            }
            return "SUCCESS";
        } catch (Exception e) {
            log.error("申请退款 回调报错：param:{"+plainText+"}");
            e.printStackTrace();
        }
        return null;
    }

}
