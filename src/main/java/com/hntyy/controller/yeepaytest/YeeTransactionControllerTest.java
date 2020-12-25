package com.hntyy.controller.yeepaytest;

import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 交易相关接口测试类
 */
public class YeeTransactionControllerTest {

    // 商户私钥
    private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3YppE52DR/KuOTNlTwpctiLYbRfjMJf2xdb8pf2rwiKkO1B+7zvVb1IaswfoL68kfHP8NXPyElZ1BOO7PJ/j8Xo9Klu/V69oy/nWLtDdz667b581mYspOSCN5ZR1gw1+H7M+G7ZySqeygGhUPc9z03p1z1CDJRW4FAi26uAn2ZtRTkYsMg+D9iVU3NPScLy4WPuX2ZIunXrf7it6WvMwl7OP1q1NKQ/gVPKuIoBb3ra5ypeJ9bJVD0euTPIlTZVn+Rdh7Um6ePspgbdRFkc/JG8QGipJRrCa/YIcm2xvxY3jgpeQmNke7UnSVbz3hqNmA9BnL0D305XGK8jAvmt+7AgMBAAECggEANy7jr0JU/ztiQHLdnvfaWChgbDqVEJGKEsGU4Z7nBjSJct3gIrq3WIfOcuP4I2gzYDpwgYvurNL8vCiurAWiRgcKZW6gAyZyxY+OvZhga3CLcL8DLwEhUYchraEAwyJnd9aJ6FdOG1Ao1VCj7790yPbIOJenugk7fyRVsG87CDsMzM5CPYAftzA6wyGWto+UxY/Dx/Ug6rY5QBZI6uWDvsDRlHzLJ2KmCULj7B1NQ8kKLzplF5SWTrvEVXcIWsP+DGntHmn/3rd5VxduSMzutlEYMvc5nj6RpF7HBuCiaSldZ6xYRDzzDwKOWC6D7kWniNlf3FeJF1NwuMRRvF5uoQKBgQDelCEduyrcNrqgSxAMN6yzpq4GjtiIctGgGel2cZSl8SNCTb4MYr8DiZh7M/MVauO522ly7CvvwJmqY56pylrdKmtgudR6YBCemP6PJTeCMFPj8EFboeBpfi2gXjcRRrUlE86QSIAqUYoSxWKtzNFCyn7GDoOC0CxPAkOt677jiwKBgQDS6+K5kmDUmYe2vC/+4wulJEgYpEYLzQMeKQGsq4FmG84Ac+7JlSgsiLi49OYARtRxzYdH1SXUPYX4taeV23djaxVX1LcodN1+KaO4wkan9HSo7fy4svydUm6pSKom6o0CsZ9/KdRlWtwH5WefqVaSfMIxH2EyUxkDOIkx1pu6kQKBgAdZdM56g6vJ0tfAIsOEgxtbgZuN0/CNegaDVIYosfPYxoVF8+SMzinbvUE0Me6fHO3iJNU6nyjHf0t1BqQsnlt3Lxx+hlmUGnhiLOWlIPQXjG2WXVIdQj+5fuAwvDjB0PFsegGhoznCf4CnK975SF+gOBdqG0WSgiQJuxpfEmqJAoGBAKjiHREPhp7UK9mCRz/klf9t1Jh+eGOcjOGKXf/e92ZF3yV3rnwUBS3bb2URGlSgYhyZP7ehkH+nn2zsLrqMFsUxCc7g0KMBKBSLzL70N9TlpL9ah19wWVqylU7QkwVECxJcHOSaHqnlHYbpBZbO5TW31Vm10YKVDNMKYrfYKasxAoGBANMkVl5Ahwmu/jgL2ETQGa1+n4kCfA6LyeVLoGoAAhseOC3z79PZ6HhF8DltKaNFEaN5gttWlHV1yhRm6Ik05zwAQWdDUWVkU0URiCdg7053BSh2y4WCFbgWg/ALJsqz7Gonbx12mG1Mg2wIeqMG8wubIkzFpsuVpiM6FGAD9uM+";

    // sass服务商 商户编号
    private static String parentMerchantNo = "10085834246";

    // 平台商 商户编号
    private static String merchantNo = "10085843316";

    private static String appKey = "app_10085834246";

    // 微信小程序appId
    private static String appId = "wx5f0e254d6e3749a2";

    /**
     * 公众号配置接口  成功
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/aggpay/wechat-config/add";
        YopRequest request = new YopRequest();

        // 平台商编号
        request.addParam("parentMerchantNo", merchantNo);
        // 1.给平台商配置填平台商编号  2.给平台商下的子账户配置的话填子商户的商户编号
        request.addParam("merchantNo", merchantNo);
        // 小程序不需要tradeAuthDirList支付授权目录，目前只做小程序支付
//        request.addParam("tradeAuthDirList", "[\"http://www.yeepay.com\",\"http://www.yeepay.com\"]");
        // subscribeAppId:推荐关注appId，目前也不需要
        request.addParam("appIdList", "[{\"appId\":\"wx5f0e254d6e3749a2\",\"appIdType\":\"MINI_PROGRAM\"}]");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 公众号配置查询  成功
     * @param args
     * @throws IOException
     */
    public static void main2(String[] args) throws IOException {
        String apiUri = "/rest/v2.0/aggpay/wechat-config/query";
        YopRequest request = new YopRequest();

        // 平台商编号
        request.addParam("parentMerchantNo", merchantNo);
        // 1.给平台商配置填平台商编号  2.给平台商下的子账户配置的话填子商户的商户编号
        request.addParam("merchantNo", merchantNo);
        request.addParam("appIdType", "MINI_PROGRAM");
        YopResponse response = YopRsaClient.get(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 交易下单(商户需先调用该接口生成预支付订单，易宝返回支付授权 token 后，再使用 token 调起相应支付。)  单笔订单
     * 平台方收款在分账给子商户: 多笔订单金额算成总价,自己处理标识以后分账填对应的入驻商户
     * @param args
     * @throws IOException
     */
    public static void main3(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/trade/order";
        YopRequest request = new YopRequest();

        request.addParam("parentMerchantNo", merchantNo);
        // 单笔收款必传 合单不传
        request.addParam("merchantNo", merchantNo);
        // 商户收款请求号
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(orderId);
        // 总订单号
        request.addParam("orderId", orderId);
        // 总订单金额
        request.addParam("orderAmount", 0.01);
        // 单笔收款必传
        request.addParam("goodsName", "测试商品001");
        // 分账
        request.addParam("fundProcessType", "DELAY_SETTLE");
        // 接收支付成功回调地址
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/payResult");
//        request.addParam("memo", merchantNo);

        // 订单过期时间，格式为YYYY-MM-DD HH:mm:ss，为空时默认在请求下单120分钟后失效，最长支持30天。使用默认时间
//        request.addParam("expiredTime", "");
        // 清算成功服务器回调地址
        request.addParam("csUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/reckoningSuccess");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 交易下单(商户需先调用该接口生成预支付订单，易宝返回支付授权 token 后，再使用 token 调起相应支付。)  合单
     * @param args
     * @throws IOException
     */
//    public static void main(String[] args) throws IOException {
//        String apiUri = "/rest/v1.0/trade/order";
//        YopRequest request = new YopRequest();
//
//        request.addParam("parentMerchantNo", merchantNo);
//        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
//        // 总订单号
//        request.addParam("orderId", orderId);
//        // 总订单金额
//        request.addParam("orderAmount", 0.02);
//        // 接收支付成功回调地址
//        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/payResult");
////        request.addParam("memo", merchantNo);
//        //合单必传
//        request.addParam("subOrderDetail", "[{\"orderId\":\"1234567890fds\",\"orderAmount\":\"0.01\",\"goodsName\":\"测试商品002\",\"fundProcessType\":\"DELAY_SETTLE\"},{\"orderAmount\":\"0.02\",\"goodsName\":\"测试商品003\",\"fundProcessType\":\"DELAY_SETTLE\"}]");
//
//        // 订单过期时间，格式为YYYY-MM-DD HH:mm:ss，为空时默认在请求下单120分钟后失效，最长支持30天。使用默认时间
////        request.addParam("expiredTime", "");
//        // 支付成功后跳转的URL  网银 银行卡快捷支付生效
////        request.addParam("redirectUrl", "");
//        // 清算成功服务器回调地址
//        request.addParam("csUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/reckoningSuccess");
//        YopResponse response = YopRsaClient.post(apiUri, request);
//        System.out.println(response.toString());
//    }

    /**
     * 聚合API收银台 微信 线下
     * @param args
     * @throws IOException
     *
     * 登录你们微信渠道商后台，在用联系人的微信扫描下载的二维码
     */
    public static void main4(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/nccashierapi/api/pay";

        YopRequest request = new YopRequest();
        // 支付工具：小程序
        request.addParam("payTool", "MINI_PROGRAM");
        // 支付类型 WECHAT ALIPAY
        request.addParam("payType", "WECHAT");
        request.addParam("token", "440A0654325338F60E59C1BB79BDF39047D721B3E7428E20056C69242AE7EEEA");
        // 微信小程序的appId
        request.addParam("appId", "wx5f0e254d6e3749a2");
        // 动态生成
        request.addParam("openId", "ogqBq5Goe-EAINEvCt1I-LFsZJwk");
        request.addParam("version", "1.0");
        // IPV4格式
        request.addParam("userIp", "192.168.0.39");
        request.addParam("extParamMap", "{\"reportFee\":\"XIANXIA\"}");

        //step3 发起请求
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

}
