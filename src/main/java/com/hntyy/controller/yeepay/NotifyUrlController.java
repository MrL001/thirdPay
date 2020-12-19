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
     * 特约商户入网(企业/个体)
     */
    @RequestMapping("/registerSaasMerchant")
    public void qualUpload(HttpServletRequest request){
        String response = request.getParameter("response");
        System.out.println(response);
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
