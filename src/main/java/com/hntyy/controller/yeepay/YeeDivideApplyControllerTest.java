package com.hntyy.controller.yeepay;

import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;

import java.io.IOException;
import java.util.UUID;

/**
 * 分账相关接口测试类
 */
public class YeeDivideApplyControllerTest {

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
     * 申请分账   成功
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/divide/apply";
        YopRequest request = new YopRequest();

        // 交易发起方商编。与交易下单传入的保持一致
        request.addParam("parentMerchantNo", merchantNo);
        // 收款商户编号
        request.addParam("merchantNo", merchantNo);
        // 商户请求收款的交易单号
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        // 收款交易对应在易宝的收款单号
        request.addParam("uniqueOrderNo", "1013202012230000001960859749");
        // 商户分账请求号
        request.addParam("divideRequestId", UUID.randomUUID().toString().replaceAll("-", ""));
        // JSON字符串；
        //ledgerNo  分账接收方编号(接收分账资金的易宝商编)
        //amount 分账金额 减去对应支付方式之后可用于分账的金额
        //divideDetailDesc 分账说明
        request.addParam("divideDetail", "[{\"ledgerNo\":\"10085848604\",\"amount\":\"0.01\",\"divideDetailDesc\":\"供应商结算\" }]");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 查询分账结果  成功
     * @param args
     * @throws IOException
     */
    public static void main2(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/divide/query";
        YopRequest request = new YopRequest();

        // 发起方商编
        request.addParam("parentMerchantNo", merchantNo);
        // 收款商户编号
        request.addParam("merchantNo", merchantNo);
        // 商户分账请求号
        request.addParam("divideRequestId", "fe037ece41aa48eb823bb1c186ed4d11");
        // 原支付订单的商户订单号
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        // 收款交易对应在易宝的单号
        request.addParam("uniqueOrderNo", "1013202012230000001960859749");
        YopResponse response = YopRsaClient.get(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 完结分账  成功
     * @param args
     * @throws IOException
     */
    public static void main3(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/divide/complete";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", merchantNo);
        // 商户收款请求号
        request.addParam("orderId", "b49c014f2502466293df44b48f4df269");
        // 易宝收款订单号
        request.addParam("uniqueOrderNo", "1013202012230000001961351701");
        // 商户分账请求号，在商户系统内部唯一（申请分账、完结分账应使用不同的商户分账请求号），同一分账请求号多次请求等同一次。
        request.addParam("divideRequestId", UUID.randomUUID().toString().replaceAll("-", ""));
        // 分账完结的原因描述
        request.addParam("divideDetailDesc", "不想分了");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 申请分账资金归还
     * @param args
     * @throws IOException
     */
    public static void main4(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/divide/back";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", merchantNo);
        request.addParam("divideBackRequestId", UUID.randomUUID().toString().replaceAll("-", ""));
        request.addParam("divideRequestId", "fe037ece41aa48eb823bb1c186ed4d11");
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        request.addParam("uniqueOrderNo", "1013202012230000001960859749");
        request.addParam("divideBackDetail", "[{\"amount\":\"0.01\",\"divideBackReason\":\"不想分了，要退款\",\"divideDetailNo\":\"1010202012230000001961286586\"}]");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 查询分账资金归还结果  完成
     * @param args
     * @throws IOException
     */
    public static void main5(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/divide/back/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", merchantNo);
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        request.addParam("uniqueOrderNo", "1013202012230000001960859749");
        request.addParam("divideBackRequestId", "987bd9e150f147a192f1ce3d5f93f9cd");
        YopResponse response = YopRsaClient.get(apiUri, request);
        System.out.println(response.toString());
    }


    /**
     * 申请退款
     * @param args
     * @throws IOException
     */
    public static void main6(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/trade/refund";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", merchantNo);
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        request.addParam("refundRequestId", UUID.randomUUID().toString().replaceAll("-", ""));
        request.addParam("uniqueOrderNo", "1013202012230000001960859749");
        request.addParam("refundAmount", "0.01");
        request.addParam("description", "想退款了");
        request.addParam("notifyUrl", "http://lsj.ngrok2.xiaomiqiu.cn/notifyUrl/tradeRefund");
        YopResponse response = YopRsaClient.post(apiUri, request);
        System.out.println(response.toString());
    }

    /**
     * 查询退款
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String apiUri = "/rest/v1.0/trade/refund/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", merchantNo);
        request.addParam("merchantNo", merchantNo);
        request.addParam("orderId", "db84f715d3274b2ab9b76154fc976aea");
        request.addParam("refundRequestId", "a5703be0fd774ed8a1e3cd4721e6964d");
        request.addParam("uniqueOrderNo", "1002202012230000001961430704");
        YopResponse response = YopRsaClient.get(apiUri, request);
        System.out.println(response.toString());
    }

}
