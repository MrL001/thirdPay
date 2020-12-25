package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hntyy.bean.yeepay.result.PayResultNotify;
import com.hntyy.bean.yeepay.result.ReckoningResultNotify;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import com.hntyy.service.yeepay.PayResultNotifyService;
import com.hntyy.service.yeepay.ReckoningResultNotifyService;
import com.hntyy.service.yeepay.RegisterSaasMerchantResultService;
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

    @ApiOperation(value="特约商户入网(企业/个体) 回调地址")
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
            String requestNo = jsonObject.getString("requestNo");
            String applicationNo = jsonObject.getString("applicationNo");
            String merchantNo = jsonObject.getString("merchantNo");
            String applicationStatus = jsonObject.getString("applicationStatus");
            String agreementSignUrl = jsonObject.getString("agreementSignUrl");
            String auditOpinion = jsonObject.getString("auditOpinion");
            if (requestNo != null && !"".equals(requestNo)){
                registerSaasMerchantResult.setRequestNo(requestNo);
            }
            if (applicationNo != null && !"".equals(applicationNo)){
                registerSaasMerchantResult.setApplicationNo(applicationNo);
            }
            if (merchantNo != null && !"".equals(merchantNo)){
                registerSaasMerchantResult.setMerchantNo(merchantNo);
            }
            if (applicationStatus != null && !"".equals(applicationStatus)){
                registerSaasMerchantResult.setApplicationStatus(applicationStatus);
            }
            if (agreementSignUrl != null && !"".equals(agreementSignUrl)){
                registerSaasMerchantResult.setAgreementSignUrl(agreementSignUrl);
            }
            if (auditOpinion != null && !"".equals(auditOpinion)){
                registerSaasMerchantResult.setAuditOpinion(auditOpinion);
            }
            registerSaasMerchantResult.setType(4);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
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
            String requestNo = jsonObject.getString("requestNo");
            String applicationNo = jsonObject.getString("applicationNo");
            String merchantNo = jsonObject.getString("merchantNo");
            String applicationStatus = jsonObject.getString("applicationStatus");
            String agreementSignUrl = jsonObject.getString("agreementSignUrl");
            String auditOpinion = jsonObject.getString("auditOpinion");
            if (requestNo != null && !"".equals(requestNo)){
                registerSaasMerchantResult.setRequestNo(requestNo);
            }
            if (applicationNo != null && !"".equals(applicationNo)){
                registerSaasMerchantResult.setApplicationNo(applicationNo);
            }
            if (merchantNo != null && !"".equals(merchantNo)){
                registerSaasMerchantResult.setMerchantNo(merchantNo);
            }
            if (applicationStatus != null && !"".equals(applicationStatus)){
                registerSaasMerchantResult.setApplicationStatus(applicationStatus);
            }
            if (agreementSignUrl != null && !"".equals(agreementSignUrl)){
                registerSaasMerchantResult.setAgreementSignUrl(agreementSignUrl);
            }
            if (auditOpinion != null && !"".equals(auditOpinion)){
                registerSaasMerchantResult.setAuditOpinion(auditOpinion);
            }
            registerSaasMerchantResult.setType(5);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
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
            String orderId = jsonObject.getString("orderId");
            String paySuccessDate = jsonObject.getString("paySuccessDate");
            String channel = jsonObject.getString("channel");
            String payWay = jsonObject.getString("payWay");
            String uniqueOrderNo = jsonObject.getString("uniqueOrderNo");
            BigDecimal orderAmount = jsonObject.getBigDecimal("orderAmount");
            BigDecimal payAmount = new BigDecimal(jsonObject.getString("payAmount"));
            String payerInfo = jsonObject.getString("payerInfo");
            String parentMerchantNo = jsonObject.getString("parentMerchantNo");
            String merchantNo = jsonObject.getString("merchantNo");
            String status = jsonObject.getString("status");
            PayResultNotify payResultNotify = new PayResultNotify();
            if (orderId != null && !"".equals(orderId)){
                payResultNotify.setOrderId(orderId);
            }
            if (paySuccessDate != null && !"".equals(paySuccessDate)){
                payResultNotify.setPaySuccessDate(paySuccessDate);
            }
            if (channel != null && !"".equals(channel)){
                payResultNotify.setChannel(channel);
            }
            if (payWay != null && !"".equals(payWay)){
                payResultNotify.setPayWay(payWay);
            }
            if (uniqueOrderNo != null && !"".equals(uniqueOrderNo)){
                payResultNotify.setUniqueOrderNo(uniqueOrderNo);
            }
            if (orderAmount != null){
                payResultNotify.setOrderAmount(orderAmount);
            }
            if (payAmount != null){
                payResultNotify.setPayAmount(payAmount);
            }
            if (payerInfo != null && !"".equals(payerInfo)){
                payResultNotify.setPayerInfo(payerInfo);
            }
            if (parentMerchantNo != null && !"".equals(parentMerchantNo)){
                payResultNotify.setParentMerchantNo(parentMerchantNo);
            }
            if (merchantNo != null && !"".equals(merchantNo)){
                payResultNotify.setMerchantNo(merchantNo);
            }
            if (status != null && !"".equals(status)){
                payResultNotify.setStatus(status);
            }
            payResultNotifyService.insert(payResultNotify);
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
            String parentMerchantNo = jsonObject.getString("parentMerchantNo");
            String merchantNo = jsonObject.getString("merchantNo");
            String orderId = jsonObject.getString("orderId");
            String uniqueOrderNo = jsonObject.getString("uniqueOrderNo");
            String status = jsonObject.getString("status");
            String orderAmount = jsonObject.getString("orderAmount");
            String csSuccessDate = jsonObject.getString("csSuccessDate");
            String merchantFee = jsonObject.getString("merchantFee");
            String ypSettleAmount = jsonObject.getString("ypSettleAmount");
            ReckoningResultNotify reckoningResultNotify = new ReckoningResultNotify();
            if (parentMerchantNo != null && !"".equals(parentMerchantNo)){
                reckoningResultNotify.setParentMerchantNo(parentMerchantNo);
            }
            if (merchantNo != null && !"".equals(merchantNo)){
                reckoningResultNotify.setMerchantNo(merchantNo);
            }
            if (orderId != null && !"".equals(orderId)){
                reckoningResultNotify.setOrderId(orderId);
            }
            if (uniqueOrderNo != null && !"".equals(uniqueOrderNo)){
                reckoningResultNotify.setUniqueOrderNo(uniqueOrderNo);
            }
            if (status != null && !"".equals(status)){
                reckoningResultNotify.setStatus(uniqueOrderNo);
            }
            if (orderAmount != null && !"".equals(orderAmount)){
                reckoningResultNotify.setOrderAmount(orderAmount);
            }
            if (csSuccessDate != null && !"".equals(csSuccessDate)){
                reckoningResultNotify.setCsSuccessDate(csSuccessDate);
            }
            if (merchantFee != null && !"".equals(merchantFee)){
                reckoningResultNotify.setMerchantFee(merchantFee);
            }
            if (ypSettleAmount != null && !"".equals(ypSettleAmount)){
                reckoningResultNotify.setYpSettleAmount(ypSettleAmount);
            }
            reckoningResultNotifyService.insert(reckoningResultNotify);
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
        System.out.println("进入申请退款成功回调地址");
        String response = request.getParameter("response");
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(response);
        try {
            //设置商户私钥
            PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
            //设置易宝公钥
            PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
            //解密验签
            dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
            //打印回调数据
            System.out.println(dto.getPlainText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
