package com.hntyy.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserCertifyOpenCertifyRequest;
import com.alipay.api.request.AlipayUserCertifyOpenInitializeRequest;
import com.alipay.api.request.AlipayUserCertifyOpenQueryRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserCertifyOpenCertifyResponse;
import com.alipay.api.response.AlipayUserCertifyOpenInitializeResponse;
import com.alipay.api.response.AlipayUserCertifyOpenQueryResponse;
import com.yeepay.g3.sdk.yop.utils.UUIDUtils;
import com.yeepay.shade.org.apache.commons.lang3.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/aliCommonController")
@Api(tags = "阿里工具类")
public class AliCommonController {

    // appId
    private String aliAppId = "2021001187625203";

    // 支付宝网关
    private String serverUrl = "https://openapi.alipay.com/gateway.do";
    // 应用私钥
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDa1ZyCBUEAHT8EnusIhd2Z4+0dHQCYxKOTK6yGUPPaxYOKSa0Uiyy4XN6puiXUAgAJ8epB++XsngRS3COt3CxcPRyYb7Ng2RYpDGsNhHkEF64sjfAO4XMyFY/PYHo9PKdchLyWUezfftTulb7NiLBI6An3EIQwo6ZkCx0ExmWntdaBBAHVXJcKKR6+C4QL03dYdF1irieDTuwLaHq1WIta29j7FrS/PO8L9inumJ90Gw1oFznZt/c2N56pZ1A4aJJ2cp9/yAJ0n6mD/mIFUON8TOauMmETfxS95u7OuN5GyYDkfY2tqyvy/WmDFce3VRgrc9fHWe9OpKRE0YTOoD4fAgMBAAECggEASjK1b0xyvqQr8tgjXu9stLoYco+b3TCHOeZMB/q4xAvGTwQuIFIUJqqugKPyIZFQDA8vW5IaYoEtfB7SJigKDnhWEsWE76iLn/1q6tlo4t9YmhjCNXlxwqCW8zyGFe9T28XR37IFe74YWpEBcdUP2Jh8kH3UJF3IlBFElJLkctlPJ/cR12ciKbQ0U/QUBvR5GKmlVqAAUgtH4VBijbQdx3zRxWj6pZLKtgB55pQ3yNd3AHKLESyb7PIOQRXV48RZlD3fbxSO/Dt/dCw/x9VBV3TdsU86UAkZvZOHlnqNtNbJpSeKrRR1PNqFDgLu3mag8u37iy2+yg3AKny3TiTu+QKBgQD3ihF2lGvupLYoD27t+ceG8hdAU/VUKk4cG6J4IRNxVtuLljolTlUTnqOXrYVSlLRgCq8s/6P95qimqeimE/MaxXG+7FxtqlrUP0qmbJN0lrKtk/b27J0KFxjOCkc5jI6zoxy9SLkbJVEy5BHSOtirQPtrPf+YSHWnpGfWqZsb/QKBgQDiUGEn4fy/eVdKaYd9/fLqaFYzfCz0ResHEUiwrY+/8LtWOki/+40sycMPVUUKzReIQgbyyK+DRobJayGLKwLnAiwv4mrU5LEPwDrQEV9C8vDhKdftQ+MdmGDUcWyy8Hx2vHB+3f3HSWJSMgfJAFKLPkinaqiZl7qgOnfUebGnSwKBgQC8/Zjfo+nTxcWdSEs1PeLsUKEX6AICRFpmc2LTvM0PV3U/WW8DVI7378KO51hG+GnOednMzVDzZMuWk1qgkdKlueFiFytqPwohykDAV10tQnEgvTtswrITJhc2F/rRIVNCK2Kh0YgiBZe89wv0KiGKfo52Dlu6dHf/ijINXmiiMQKBgD7yBFPHczj+Ut0LhPfyDgJMis0/GHHIkJoioIkjfPvxxOkyxNFA8f8nDu3Q1acvbAAl/TDByK9Gim6zMV031VLTM98NppEYGnc7DCCa9Ff2ojC+JqSDYUP2AdiUw5rxcGzF9LQUomUvb/1UlP2+7TZ1WFfYPpSZsvqlyHZ8coEJAoGALlFIOCBe2eLlawvCoaJKNIu2SD+HnUEO3FI7npX7WVMaZFvAZgUN5bjVkV6MLyH+vnEqydpvqxh6dz+1S28E0v0QqsISeBjML9oUEE0zhRAIVsUKF3sMfT5kCrsXV/VSLKL9FQ3G5duFdMQvUxgB54iShjjYaO30r0fdgolkw2Q=";
    // 支付宝公钥
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmG1RLpvEijEPBY83uDE1sdpMA/aqT57IX8kbX540EWaY/8LtLpyV3ueZA3F4NxHao+nZ1UlzfJqKKEFqdP5DxOzYo3iN+u0z03jbyMtPW/5B+HmjojwrFQ8Kzkv/37k0HHh2dt0mVneEmUCyqRlno/08GMdn8OOrGy3k1LTP8Gxh40DckmCmObhARJhSVdPF8B74Cem8JU1bagxD4Hl/7lNhBT8AM2HT93zJjYpU9ueQoXwl/YCoiF981vgCjHETu+HW6uPwHtLyL2+MxF4Fr52qfwUxRFiSTN5T30VCe8Q/6PujILD/fVa1jkdMN5Dx26Y8x6BL09DaY33mIfvKpQIDAQAB";

    @Autowired
    private AlipayClient alipayClient;

    @ApiOperation(value="获取userId")
    @RequestMapping(value = "/getAliUserId",method = RequestMethod.GET)
    public String getAliUserId(@ApiParam(value = "authCode",required = true)@RequestParam(name = "authCode") String authCode) throws Exception {
        log.info("---------------authCode:"+authCode);
        AlipaySystemOauthTokenResponse response = getAccessToken(authCode);
        log.info("------------------ response:"+ JSON.toJSONString(response));
        String userId = response.getUserId();
        return userId;
    }

    /**
     * 开始认证服务调用
     *
     * @param realName 真实姓名
     * @param idNum    身份证号码
     * @return         url-支付宝认证链接
     */
    @ApiOperation(value="认证服务调用")
    @RequestMapping(value = "/startCertify",method = RequestMethod.GET)
    public String startCertify(String realName, String idNum) {
        String certifyId = authInitialize(realName, idNum);
        if (StringUtils.isBlank(certifyId)) {
            return null;
        }

        AlipayUserCertifyOpenCertifyRequest request = new AlipayUserCertifyOpenCertifyRequest();
        //设置certifyId
        JSONObject bizContentObj = new JSONObject();
        bizContentObj.put("certify_id", certifyId);
        request.setBizContent(bizContentObj.toString());

        //生成请求链接，这里一定要使用GET模式
        try {
            AlipayUserCertifyOpenCertifyResponse response = alipayClient.pageExecute(request, "GET");
            if (response.isSuccess()) {
                log.info("[身份验证]开始认证服务调用成功:{}", realName);
                return response.getBody();
            } else {
                log.info("[身份验证]开始认证服务调用失败:{}", realName);
                return null;
            }
        } catch (AlipayApiException e) {
            log.error("[身份验证]开始认证服务调用失败:{}", realName, e);
            return null;
        }
    }

    /**
     * 查询身份认证结果
     *
     * @param certifyId 需要你在你的业务中获取之前保存下来的 certify_id
     * @return
     */
    @ApiOperation(value="查询身份认证结果")
    @RequestMapping(value = "/queryCertifyResult",method = RequestMethod.GET)
    public boolean queryCertifyResult(String certifyId) {
        AlipayUserCertifyOpenQueryRequest request = new AlipayUserCertifyOpenQueryRequest();
        JSONObject bizContentObj = new JSONObject();
        bizContentObj.put("certify_id", certifyId);
        request.setBizContent(bizContentObj.toString());
        try {
            AlipayUserCertifyOpenQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("[身份验证]认证查询服务调用成功:{}", certifyId);
                // 返回的是个json字符串
                String body = response.getBody();
                if (StringUtils.isNotBlank(body)) {
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    // 我们需要获取 alipay_user_certify_open_query_response 下的值
                    JSONObject queryResponse = jsonObject.getJSONObject("alipay_user_certify_open_query_response");
                    log.info(body); // 你可以打印看一下 json 结构
                    //这里需要注意认证通过的条件 官方文档显示 passed 是个数组，但是它只是一个 String
                    if (StringUtils.equals(queryResponse.getString("code"), "10000") && StringUtils.equals(queryResponse.getString("passed"), "T")) {
                        log.info("[身份验证]认证通过:{}", certifyId);
                        return true;
                    } else {
                        log.info("[身份验证]认证失败:{}", certifyId);
                        return false;
                    }
                }
            } else {
                log.info("[身份验证]认证查询服务调用失败:{}", certifyId);
            }
        } catch (AlipayApiException e) {
            log.error("[身份验证]开始认证服务调用失败:{}", certifyId, e);
        }
        return false;
    }

    // 服务端获取access_token、user_id
    private AlipaySystemOauthTokenResponse getAccessToken(String authCode) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,aliAppId,privateKey,"json","GBK",publicKey,"RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);        // 4. 填入前端传入的授权码authCode
        request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");    // 0. 不用管
        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 初始化身份认证
     *
     * @param realName 真实姓名
     * @param idNum    身份证号码
     * @return
     */
    public String authInitialize(String realName, String idNum) {
        AlipayUserCertifyOpenInitializeRequest request = new AlipayUserCertifyOpenInitializeRequest();

        //构造身份信息json对象  JSONObject 阿里的fastjson
        JSONObject identityObj = new JSONObject();
        //身份类型，必填
        identityObj.put("identity_type", "CERT_INFO");
        //证件类型，必填
        identityObj.put("cert_type", "IDENTITY_CARD");
        //真实姓名，必填
        identityObj.put("cert_name", realName);
        //证件号码，必填
        identityObj.put("cert_no", idNum);

        //构造商户配置json对象
        JSONObject merchantConfigObj = new JSONObject();
        try {
            //认证完成后手机支付宝的返回页面，我这里返回首页
            String returnUrl = URLEncoder.encode("https://m.alipay.com/Gk8NF23", "UTF-8");
            // 设置回调地址,必填. 如果需要直接在支付宝APP里面打开回调地址使用alipay协议，参考下面的案例：appId用固定值 20000067，url替换为urlEncode后的业务回跳地址
            // alipays://platformapi/startapp?appId=20000067&url=https%3A%2F%2Fapp.cqkqinfo.com%2Fcertify%2FzmxyBackNew.do
            merchantConfigObj.put("return_url", "alipays://platformapi/startapp?appId=20000067&url=" + returnUrl);

            //构造身份认证初始化服务业务参数数据
            JSONObject bizContentObj = new JSONObject();
            //商户请求的唯一标识，推荐为uuid，必填  IdUtil hutool工具
            bizContentObj.put("outer_order_no", UUID.randomUUID().toString().replaceAll("-", ""));
            bizContentObj.put("biz_code", "FACE");
            bizContentObj.put("identity_param", identityObj);
            bizContentObj.put("merchant_config", merchantConfigObj);
            request.setBizContent(bizContentObj.toString());

            //发起请求
            AlipayUserCertifyOpenInitializeResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("[身份验证]初始化身份认证成功:{}", realName);
                //接口调用成功，从返回对象中获取certify_id
                String certifyId = response.getCertifyId();
                // 生成的certifyId 是查询认证结果的凭证，需要保存下来，我这是保存到数据库中，也可保存到redis
//                userMapper.insertCertifyId(idNum, certifyId);
                return certifyId;
            } else {
                log.info("[身份验证]初始化身份认证失败:{}", realName);
                return null;
            }
        } catch (Exception e) {
            log.error("[身份验证]初始化身份认证失败:{}", realName, e);
            return null;
        }
    }

}
