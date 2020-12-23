package com.hntyy.controller.yeepay;

import com.yeepay.g3.sdk.yop.encrypt.CertTypeEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigitalEnvelopeDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理回调地址
 */
@Slf4j
@RestController
@RequestMapping("/notifyUrl")
public class NotifyUrlController {

    /**
     * 特约商户入网(企业/个体) 回调地址
     */
    @RequestMapping("/registerSaasMerchant")
    public void qualUpload(HttpServletRequest request){
        System.out.println("进入特约商户入网(企业/个体) 回调地址");
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
    }

    /**
     * 商户产品费率变更 回调地址
     */
    @RequestMapping("/merProductFeeModify")
    public void merProductFeeModify(HttpServletRequest request){
        String response = request.getParameter("response");
        System.out.println("进入商户产品费率变更 回调地址");
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
    }

    /**
     * 支付结果 回调地址
     */
    @RequestMapping("/payResult")
    public void payResult(HttpServletRequest request){
        System.out.println("进入支付结果回调地址");
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
    }

    /**
     * 清算成功 回调地址
     */
    @RequestMapping("/reckoningSuccess")
    public void reckoningSuccess(HttpServletRequest request){
        System.out.println("进入清算成功回调地址");
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
    }

    /**
     * 申请退款 回调地址
     */
    @RequestMapping("/tradeRefund")
    public void tradeRefund(HttpServletRequest request){
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
    }

}
