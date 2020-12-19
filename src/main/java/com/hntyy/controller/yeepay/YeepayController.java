package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.hntyy.bean.yeepay.query.MerchantSubjectInfo;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantQuery;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("yeepayController")
@Api("易宝支付：商户入网相关接口")
public class YeepayController {

    private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3YppE52DR/KuOTNlTwpctiLYbRfjMJf2xdb8pf2rwiKkO1B+7zvVb1IaswfoL68kfHP8NXPyElZ1BOO7PJ/j8Xo9Klu/V69oy/nWLtDdz667b581mYspOSCN5ZR1gw1+H7M+G7ZySqeygGhUPc9z03p1z1CDJRW4FAi26uAn2ZtRTkYsMg+D9iVU3NPScLy4WPuX2ZIunXrf7it6WvMwl7OP1q1NKQ/gVPKuIoBb3ra5ypeJ9bJVD0euTPIlTZVn+Rdh7Um6ePspgbdRFkc/JG8QGipJRrCa/YIcm2xvxY3jgpeQmNke7UnSVbz3hqNmA9BnL0D305XGK8jAvmt+7AgMBAAECggEANy7jr0JU/ztiQHLdnvfaWChgbDqVEJGKEsGU4Z7nBjSJct3gIrq3WIfOcuP4I2gzYDpwgYvurNL8vCiurAWiRgcKZW6gAyZyxY+OvZhga3CLcL8DLwEhUYchraEAwyJnd9aJ6FdOG1Ao1VCj7790yPbIOJenugk7fyRVsG87CDsMzM5CPYAftzA6wyGWto+UxY/Dx/Ug6rY5QBZI6uWDvsDRlHzLJ2KmCULj7B1NQ8kKLzplF5SWTrvEVXcIWsP+DGntHmn/3rd5VxduSMzutlEYMvc5nj6RpF7HBuCiaSldZ6xYRDzzDwKOWC6D7kWniNlf3FeJF1NwuMRRvF5uoQKBgQDelCEduyrcNrqgSxAMN6yzpq4GjtiIctGgGel2cZSl8SNCTb4MYr8DiZh7M/MVauO522ly7CvvwJmqY56pylrdKmtgudR6YBCemP6PJTeCMFPj8EFboeBpfi2gXjcRRrUlE86QSIAqUYoSxWKtzNFCyn7GDoOC0CxPAkOt677jiwKBgQDS6+K5kmDUmYe2vC/+4wulJEgYpEYLzQMeKQGsq4FmG84Ac+7JlSgsiLi49OYARtRxzYdH1SXUPYX4taeV23djaxVX1LcodN1+KaO4wkan9HSo7fy4svydUm6pSKom6o0CsZ9/KdRlWtwH5WefqVaSfMIxH2EyUxkDOIkx1pu6kQKBgAdZdM56g6vJ0tfAIsOEgxtbgZuN0/CNegaDVIYosfPYxoVF8+SMzinbvUE0Me6fHO3iJNU6nyjHf0t1BqQsnlt3Lxx+hlmUGnhiLOWlIPQXjG2WXVIdQj+5fuAwvDjB0PFsegGhoznCf4CnK975SF+gOBdqG0WSgiQJuxpfEmqJAoGBAKjiHREPhp7UK9mCRz/klf9t1Jh+eGOcjOGKXf/e92ZF3yV3rnwUBS3bb2URGlSgYhyZP7ehkH+nn2zsLrqMFsUxCc7g0KMBKBSLzL70N9TlpL9ah19wWVqylU7QkwVECxJcHOSaHqnlHYbpBZbO5TW31Vm10YKVDNMKYrfYKasxAoGBANMkVl5Ahwmu/jgL2ETQGa1+n4kCfA6LyeVLoGoAAhseOC3z79PZ6HhF8DltKaNFEaN5gttWlHV1yhRm6Ik05zwAQWdDUWVkU0URiCdg7053BSh2y4WCFbgWg/ALJsqz7Gonbx12mG1Mg2wIeqMG8wubIkzFpsuVpiM6FGAD9uM+";

    // 商户编号
    private static String parentMerchantNo = "10085834246";

    /**
     * 子商户入网资质文件上传
     * @param merQual
     */
    @RequestMapping("/qualUpload")
    @PutMapping("add")
    @ApiOperation(value="商品新增")
    public String qualUpload(String merQual){
        String apiUri = "/yos/v1.0/sys/merchant/qual/upload";
        //arg0:appkey（举例授权扣款是SQKK+商户编号，亿企通是OPR:+商户编号，具体是什么请参考自己开通产品的手册。
        //arg1:商户私钥，不传这个参数的时候，默认使用配置文件里的isv_private_key
        YopRequest request = new YopRequest("app_10085834246",privateKey);
        try {
            request.addFile("merQual",new URL(merQual).openStream());
        } catch (IOException e) {
            log.error("qualUpload方法->url地址错误，请求参数：merQual:"+merQual);
            e.printStackTrace();
        }
        YopResponse response = null;
        try {
            response = YopRsaClient.upload(apiUri, request);
        } catch (IOException e) {
            log.error("qualUpload方法，YopRsaClient.upload出错->url地址错误，请求参数：merQual:"+merQual);
            e.printStackTrace();
        }
        System.out.println(response.toString());
        return response.toString();
    }

    /**
     * 特约商户入网(企业/个体)
     * @param registerSaasMerchantQuery
     */
    @RequestMapping("/registerSaasMerchant")
    public String registerSaasMerchant(RegisterSaasMerchantQuery registerSaasMerchantQuery){
        // 造数据
        registerSaasMerchantQuery.setRequestNo(UUID.randomUUID().toString().replaceAll("-", ""));
        registerSaasMerchantQuery.setParentMerchantNo(parentMerchantNo);
        registerSaasMerchantQuery.setBusinessRole("ORDINARY_MERCHANT");
        MerchantSubjectInfo merchantSubjectInfo;
//        registerSaasMerchantQuery.setMerchantSubjectInfo();
//        registerSaasMerchantQuery.setMerchantCorporationInfo();
//        registerSaasMerchantQuery.setMerchantContactInfo();
//        registerSaasMerchantQuery.setBusinessAddressInfo();
//        registerSaasMerchantQuery.setNotifyUrl();
//        registerSaasMerchantQuery.setProductInfo();


        String apiUri = "/rest/v2.0/mer/register/saas/merchant";
        YopRequest request = new YopRequest();

        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(requestNo);
        request.addParam("requestNo", registerSaasMerchantQuery.getRequestNo());
        request.addParam("parentMerchantNo", registerSaasMerchantQuery.getParentMerchantNo());
        // 入网商户业务角色:  ORDINARY_MERCHANT:标准商户  PLATFORM_MERCHANT:平台商  SETTLED_MERCHANT:入驻商户
        request.addParam("businessRole", registerSaasMerchantQuery.getBusinessRole());
        request.addParam("merchantSubjectInfo", registerSaasMerchantQuery.getMerchantSubjectInfo());
        request.addParam("merchantCorporationInfo", registerSaasMerchantQuery.getMerchantCorporationInfo());
        request.addParam("merchantContactInfo", registerSaasMerchantQuery.getMerchantContactInfo());
//        request.addParam("industryCategoryInfo", "");
        request.addParam("businessAddressInfo", registerSaasMerchantQuery.getBusinessAddressInfo());
//        request.addParam("settlementAccountInfo", "");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/registerSaasMerchant");
        request.addParam("productInfo", registerSaasMerchantQuery.getProductInfo());


        //step3 发起请求
        YopResponse response = null;
        try {
            response = YopRsaClient.post(apiUri, request);
        } catch (IOException e) {
            log.error("registerSaasMerchant方法出错->url地址错误，请求参数：registerSaasMerchantQuery:"+ JSON.toJSONString(registerSaasMerchantQuery));
            e.printStackTrace();
        }
        return response.toString();
    }



}
