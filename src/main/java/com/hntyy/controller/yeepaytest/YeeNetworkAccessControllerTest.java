package com.hntyy.controller.yeepaytest;

import com.alibaba.fastjson.JSON;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 入网相关接口测试类
 */
public class YeeNetworkAccessControllerTest {

    // 商户私钥
    private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3YppE52DR/KuOTNlTwpctiLYbRfjMJf2xdb8pf2rwiKkO1B+7zvVb1IaswfoL68kfHP8NXPyElZ1BOO7PJ/j8Xo9Klu/V69oy/nWLtDdz667b581mYspOSCN5ZR1gw1+H7M+G7ZySqeygGhUPc9z03p1z1CDJRW4FAi26uAn2ZtRTkYsMg+D9iVU3NPScLy4WPuX2ZIunXrf7it6WvMwl7OP1q1NKQ/gVPKuIoBb3ra5ypeJ9bJVD0euTPIlTZVn+Rdh7Um6ePspgbdRFkc/JG8QGipJRrCa/YIcm2xvxY3jgpeQmNke7UnSVbz3hqNmA9BnL0D305XGK8jAvmt+7AgMBAAECggEANy7jr0JU/ztiQHLdnvfaWChgbDqVEJGKEsGU4Z7nBjSJct3gIrq3WIfOcuP4I2gzYDpwgYvurNL8vCiurAWiRgcKZW6gAyZyxY+OvZhga3CLcL8DLwEhUYchraEAwyJnd9aJ6FdOG1Ao1VCj7790yPbIOJenugk7fyRVsG87CDsMzM5CPYAftzA6wyGWto+UxY/Dx/Ug6rY5QBZI6uWDvsDRlHzLJ2KmCULj7B1NQ8kKLzplF5SWTrvEVXcIWsP+DGntHmn/3rd5VxduSMzutlEYMvc5nj6RpF7HBuCiaSldZ6xYRDzzDwKOWC6D7kWniNlf3FeJF1NwuMRRvF5uoQKBgQDelCEduyrcNrqgSxAMN6yzpq4GjtiIctGgGel2cZSl8SNCTb4MYr8DiZh7M/MVauO522ly7CvvwJmqY56pylrdKmtgudR6YBCemP6PJTeCMFPj8EFboeBpfi2gXjcRRrUlE86QSIAqUYoSxWKtzNFCyn7GDoOC0CxPAkOt677jiwKBgQDS6+K5kmDUmYe2vC/+4wulJEgYpEYLzQMeKQGsq4FmG84Ac+7JlSgsiLi49OYARtRxzYdH1SXUPYX4taeV23djaxVX1LcodN1+KaO4wkan9HSo7fy4svydUm6pSKom6o0CsZ9/KdRlWtwH5WefqVaSfMIxH2EyUxkDOIkx1pu6kQKBgAdZdM56g6vJ0tfAIsOEgxtbgZuN0/CNegaDVIYosfPYxoVF8+SMzinbvUE0Me6fHO3iJNU6nyjHf0t1BqQsnlt3Lxx+hlmUGnhiLOWlIPQXjG2WXVIdQj+5fuAwvDjB0PFsegGhoznCf4CnK975SF+gOBdqG0WSgiQJuxpfEmqJAoGBAKjiHREPhp7UK9mCRz/klf9t1Jh+eGOcjOGKXf/e92ZF3yV3rnwUBS3bb2URGlSgYhyZP7ehkH+nn2zsLrqMFsUxCc7g0KMBKBSLzL70N9TlpL9ah19wWVqylU7QkwVECxJcHOSaHqnlHYbpBZbO5TW31Vm10YKVDNMKYrfYKasxAoGBANMkVl5Ahwmu/jgL2ETQGa1+n4kCfA6LyeVLoGoAAhseOC3z79PZ6HhF8DltKaNFEaN5gttWlHV1yhRm6Ik05zwAQWdDUWVkU0URiCdg7053BSh2y4WCFbgWg/ALJsqz7Gonbx12mG1Mg2wIeqMG8wubIkzFpsuVpiM6FGAD9uM+";

    // sass服务商 商户编号
    private static String parentMerchantNo = "10085834246";

    // 平台商 商户编号
    private static String merchantNo = "10085843316";

    // 东方龙商户编号
    private static String sonMerchantNo = "10085848604";

    private static String appKey = "app_10085834246";

    /**
     * 子商户入网资质文件上传 成功
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        /* 上传文件*/
        //上传文件目前支持文件流，远程文件，本地文件3个模式，下面例子为本地模式

        /**
         * 接口传参 fileUrl
         */

        //请求URI
        String apiUri = "/yos/v1.0/sys/merchant/qual/upload";

        //step1  生成yop请求对象
        //arg0:appkey（举例授权扣款是SQKK+商户编号，亿企通是OPR:+商户编号，具体是什么请参考自己开通产品的手册。
        //arg1:商户私钥，不传这个参数的时候，默认使用配置文件里的isv_private_key
        YopRequest request = new YopRequest(appKey,privateKey);

        //step2 配置参数
        //arg0:固定用merQual
        //arg1:文件路径

        // 本地文件模式（可选）
         request.addFile("merQual",new File("D:/入网资料/东方龙餐饮/手持营业执照.JPG"));

        // 远程文件模式（可选）
//        request.addFile("merQual",new URL("https://imgs.yeepay.com/img/1533693637515_shouye0808.jpg").openStream());

        // 流模式（可选）
        // InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("privatekey")));
        // request.addFile("merQual", inputStream);


        //step3 发起请求
        //arg0:接口的uri（参见手册）
        //arg1:配置好参数的请求对象
        YopResponse response = YopRsaClient.upload(apiUri, request);
        QualUploadResult qualUploadResult = new QualUploadResult();
        Map result = (Map) response.getResult();
        qualUploadResult.setMerQualUrl((String) result.get("merQualUrl"));
        qualUploadResult.setReturnCode((String) result.get("returnCode"));
        qualUploadResult.setReturnMsg((String) result.get("returnMsg"));
        System.out.println(JSON.toJSONString(qualUploadResult));
    }

    /**
     * 特约商户入网(企业/个体) 铭记金泽入网  成功
     * @param args
     * @throws IOException
     */
    public static void main2(String[] args) throws IOException {
//        RegisterSaasMerchantQuery registerSaasMerchantQuery = new RegisterSaasMerchantQuery();
        // ......

//        MerchantSubjectInfo merchantSubjectInfo = new MerchantSubjectInfo();
//        merchantSubjectInfo.setHandLicenceUrl("hello");
//        merchantSubjectInfo.setLicenceNo("3232323");
//        merchantSubjectInfo.setSignName("你好");
//        System.out.println(JSON.toJSONString(merchantSubjectInfo));

        String apiUri = "/rest/v2.0/mer/register/saas/merchant";

//        YopRequest request = new YopRequest(appKey,privateKey);
        YopRequest request = new YopRequest();
        //step2 配置业务参数（根据对接业务参考相应手册的参数列表）
        // requestNo入网请求号（自定义）
        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(requestNo);
        request.addParam("requestNo", requestNo);
        request.addParam("parentMerchantNo", parentMerchantNo);
        // 入网商户业务角色:  ORDINARY_MERCHANT:标准商户  PLATFORM_MERCHANT:平台商  SETTLED_MERCHANT:入驻商户
        request.addParam("businessRole", "PLATFORM_MERCHANT");
        request.addParam("merchantSubjectInfo", "{ \"signType\":\"ENTERPRISE\", \"licenceNo\":\"91430111MA4QQ5B118\", \"licenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/18/merchant-1608273930695-9G1RCKaaR2O_6Eizf9U5Kg-KurWUDsTjcEqlulIPDhk.JPG\", \"signName\":\"湖南铭记今泽科技有限公司\", \"shortName\":\"校源汇\",\"openAccountLicenceNo\":\"J5510062978701\",\"openAccountLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/18/merchant-1608274679602-60mKaHA3TEe3VOhNwRNHCw-YRuVwdIXLpSrjFuWKCVv.JPG\",\"handLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/18/merchant-1608284917428-Uy0Oi0yTQW25wyWAonKlyA-CDbalKoPRBjVJafxvzBV.JPG\" }");
        request.addParam("merchantCorporationInfo", "{ \"legalName\":\"马伟\", \"legalLicenceType\":\"ID_CARD\", \"legalLicenceNo\":\"412921197908170714\", \"legalLicenceFrontUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/18/merchant-1608275100824-9RLltAWMQAeijuQ3lTjT0w-QUcsVvfbdTHyglexfpln.PNG\", \"legalLicenceBackUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/18/merchant-1608275142648-n6nXMFfmSU-O7cB3gW6LYw-TqAmGLaiOnbFlQtyrxbN.PNG\" }");
        request.addParam("merchantContactInfo", "{ \"contactName\":\"陈强\", \"contactMobile\":\"18874149131\", \"contactEmail\":\"xyh@mjjzxyh.com\", \"contactLicenceNo\":\"4301241987071086551\" }");
//        request.addParam("industryCategoryInfo", "");
        request.addParam("businessAddressInfo", "{ \"province\":\"430000\", \"city\":\"430100\", \"district\":\"430111\", \"address\":\"湘府中路18号德思勤城市广场A3栋2112房\" }");
        request.addParam("settlementAccountInfo", "{ \"settlementDirection\":\"BANKCARD\", \"bankCode\":\"ICBC\", \"bankAccountType\":\"ENTERPRISE_ACCOUNT\", \"bankCardNo\":\"1901005019200129363\" }");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/registerSaasMerchant");
        request.addParam("productInfo", "[{\"productCode\":\"USER_SCAN_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"USER_SCAN_WECHAT_ONLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.63\"},{\"productCode\":\"USER_SCAN_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"MERCHANT_SCAN_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"MERCHANT_SCAN_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"WECHAT_OFFIACCOUNT_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"WECHAT_OFFIACCOUNT_WECHAT_ONLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.63\"},{\"productCode\":\"ALIPAY_LIFE_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"MINI_PROGRAM_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"MINI_PROGRAM_WECHAT_ONLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.63\"},{\"productCode\":\"MINI_PROGRAM_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"ONEKEYPAY_DEBIT\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.3\"},{\"productCode\":\"ONEKEYPAY_CREDIT\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.55\"},{\"productCode\":\"D1\",\"rateType\":\"SINGLE_FIXED\",\"fixedRate\":\"1\"}]");

        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());

        Map result = (Map) response.getResult();
        RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
        registerSaasMerchantResult.setReturnCode((String) result.get("returnCode"));
        registerSaasMerchantResult.setReturnMsg((String) result.get("returnMsg"));
        registerSaasMerchantResult.setRequestNo((String) result.get("requestNo"));
        registerSaasMerchantResult.setApplicationNo((String) result.get("applicationNo"));
        registerSaasMerchantResult.setApplicationStatus((String) result.get("applicationStatus"));
        System.out.println(JSON.toJSONString(registerSaasMerchantResult));

        return ;
    }


    /**
     * 特约商户入网(企业/个体) 其他入驻商户入网  成功
     * @param args
     * @throws IOException
     */
    public static void main3(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/mer/register/saas/merchant";

//        YopRequest request = new YopRequest(appKey,privateKey);
        YopRequest request = new YopRequest();
        //step2 配置业务参数（根据对接业务参考相应手册的参数列表）
        // requestNo入网请求号（自定义）
        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(requestNo);
        request.addParam("requestNo", requestNo);
        request.addParam("parentMerchantNo", merchantNo);
        // 入网商户业务角色:  ORDINARY_MERCHANT:标准商户  PLATFORM_MERCHANT:平台商  SETTLED_MERCHANT:入驻商户
        request.addParam("businessRole", "SETTLED_MERCHANT");
        request.addParam("merchantSubjectInfo", "{ \"signType\":\"ENTERPRISE\", \"licenceNo\":\"91430111MA4L3L082K\", \"licenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608171070120-FbsP9xxiSkKPKSz9cRCJeg-yblBnBoDjFGeIUBBENad.JPG\", \"signName\":\"武汉东方龙餐饮管理有限公司湖南分公司\", \"shortName\":\"东方龙餐饮\",\"openAccountLicenceNo\":\"5510-01475423\",\"openAccountLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608171515228-TOsIhNT2S9-zRWqJ5QvLEw-lhJMkAqHaqAjBgEjPNVo.JPG\",\"handLicenceUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/21/merchant-1608542531175-WZdC9I9rQqq3YQm73ilytg-trFnAwXSXcqTMARXaiUL.JPG\" }");
        request.addParam("merchantCorporationInfo", "{ \"legalName\":\"马中华\", \"legalLicenceType\":\"ID_CARD\", \"legalLicenceNo\":\"411321197612220723\", \"legalLicenceFrontUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608172320236-kQD9VUzVQR-9Ur57OWZuGQ-EGnBwFrwccevQCdjCUiS.PNG\", \"legalLicenceBackUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/17/merchant-1608172399569-WwHyWIclS1Sf6RreI-wsjQ-COpEglcveXNlwGnldoMM.PNG\" }");
        request.addParam("merchantContactInfo", "{ \"contactName\":\"陈强\", \"contactMobile\":\"18874149131\", \"contactEmail\":\"xyh@mjjzxyh.com\", \"contactLicenceNo\":\"4301241987071086551\" }");
//        request.addParam("industryCategoryInfo", "");
        request.addParam("businessAddressInfo", "{ \"province\":\"430000\", \"city\":\"430100\", \"district\":\"430111\", \"address\":\"迎新路868号德思勤城市广场A-1项目第A3栋2113号\" }");
        request.addParam("settlementAccountInfo", "{ \"settlementDirection\":\"BANKCARD\", \"bankCode\":\"CCB\", \"bankAccountType\":\"ENTERPRISE_ACCOUNT\", \"bankCardNo\":\"43050177373600000071\" }");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/registerSaasMerchant");
        request.addParam("productInfo", "[{\"productCode\":\"MINI_PROGRAM_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.6\"},{\"productCode\":\"MINI_PROGRAM_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.6\"},{\"productCode\":\"D1\",\"rateType\":\"SINGLE_FIXED\",\"fixedRate\":\"1\"}]");

        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());

        Map result = (Map) response.getResult();
        RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
        registerSaasMerchantResult.setReturnCode((String) result.get("returnCode"));
        registerSaasMerchantResult.setReturnMsg((String) result.get("returnMsg"));
        registerSaasMerchantResult.setRequestNo((String) result.get("requestNo"));
        registerSaasMerchantResult.setApplicationNo((String) result.get("applicationNo"));
        registerSaasMerchantResult.setApplicationStatus((String) result.get("applicationStatus"));
        System.out.println(JSON.toJSONString(registerSaasMerchantResult));

        return ;
    }

    /**
     * 小微入网 成功 非人工，处理快
     * @param args
     */
    public static void main4(String[] args) throws IOException {
//        RegisterSaasMicroQuery registerSaasMicroQuery = new RegisterSaasMicroQuery();
        String apiUri = "/rest/v2.0/mer/register/saas/micro";

        YopRequest request = new YopRequest();

        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(requestNo);
        request.addParam("requestNo", requestNo);
        request.addParam("parentMerchantNo", merchantNo);
        // 填姓名即可
        request.addParam("merchantSubjectInfo", "{ \"signName\":\"罗送军\", \"shortName\":\"罗\" }");
        request.addParam("merchantCorporationInfo", "{ \"legalLicenceType\":\"ID_CARD\", \"legalLicenceNo\":\"500233199401253111\", \"legalLicenceFrontUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/19/merchant-1608367744871-d97J6WtTQQadQa2n0I6AjA-OVfcmPajgdIdjFgWCmRc.JPG\", \"legalLicenceBackUrl\":\"http://staticres.yeepay.com/jcptb-merchant-netinjt03/2020/12/19/merchant-1608367785671-qqjlLfdbT6uF0v7ftRxJdw-OtzHursuGOWnXnuNEGBs.JPG\", \"mobile\":\"17680329357\" }");
        // 填身份证地址即可
        request.addParam("businessAddressInfo", "{ \"province\":\"430000\", \"city\":\"431000\", \"district\":\"431002\", \"address\":\"万华岩镇下凤村13组\" }");
        request.addParam("accountInfo", "{\"bankAccountType\":\"DEBIT_CARD\",\"bankCardNo\":\"6214837403472339\",\"bankCode\":\"CMBCHINA\"}}");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/registerSaasMerchant");
        // 测试随意填的即可
        request.addParam("productInfo", "[{\"productCode\":\"MINI_PROGRAM_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.1\"},{\"productCode\":\"D1\",\"rateType\":\"SINGLE_FIXED\",\"fixedRate\":\"0.1\"}]");


        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());

    }

    /**
     * 商户入网进度查询接口  成功
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/mer/register/query";
        YopRequest request = new YopRequest();
        // 入网请求号
        String requestNo = "TYSHRW20201221172313181842";
        request.addParam("requestNo", requestNo);
        YopResponse response = YopRsaClient.get(apiUri, request);
        Map result = (Map) response.getResult();
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 商户产品费率变更 完成
     * @param args
     */
    public static void main6(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/mer/product/fee/modify";
        YopRequest request = new YopRequest();
        String requestNo = UUID.randomUUID().toString().replaceAll("-", "");
        request.addParam("requestNo", requestNo);
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", sonMerchantNo);
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/merProductFeeModify");
        request.addParam("productInfo", "[{\"productCode\":\"MINI_PROGRAM_WECHAT_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.61\",\"undertaker\":\"SETTLED_MERCHANT\",\"paymentMethod\":\"REAL_TIME\"},{\"productCode\":\"MINI_PROGRAM_ALIPAY_OFFLINE\",\"rateType\":\"SINGLE_PERCENT\",\"percentRate\":\"0.61\",\"undertaker\":\"SETTLED_MERCHANT\",\"paymentMethod\":\"REAL_TIME\"},{\"productCode\":\"D1\",\"rateType\":\"SINGLE_FIXED\",\"fixedRate\":\"2\",\"undertaker\":\"SETTLED_MERCHANT\",\"paymentMethod\":\"REAL_TIME\"}]");

        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());

    }

    /**
     * 商户产品费率查询 完成
     * @param args
     */
    public static void main7(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/mer/product/fee/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        // 子商户编号
        request.addParam("merchantNo", sonMerchantNo);
        //step3 发起请求
        YopResponse response = YopRsaClient.get(apiUri, request);
        System.out.println(response.toString());
    }

}
