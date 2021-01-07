package com.hntyy.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启支付宝客户端
 * @Author zjl
 * @Date 2020/9/15 9:33
 */
@Configuration
public class AliCilent {

    // appId
    private String aliAppId = "2021001187625203";

    // 支付宝网关
    private String serverUrl = "https://openapi.alipay.com/gateway.do";
    // 应用私钥
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDa1ZyCBUEAHT8EnusIhd2Z4+0dHQCYxKOTK6yGUPPaxYOKSa0Uiyy4XN6puiXUAgAJ8epB++XsngRS3COt3CxcPRyYb7Ng2RYpDGsNhHkEF64sjfAO4XMyFY/PYHo9PKdchLyWUezfftTulb7NiLBI6An3EIQwo6ZkCx0ExmWntdaBBAHVXJcKKR6+C4QL03dYdF1irieDTuwLaHq1WIta29j7FrS/PO8L9inumJ90Gw1oFznZt/c2N56pZ1A4aJJ2cp9/yAJ0n6mD/mIFUON8TOauMmETfxS95u7OuN5GyYDkfY2tqyvy/WmDFce3VRgrc9fHWe9OpKRE0YTOoD4fAgMBAAECggEASjK1b0xyvqQr8tgjXu9stLoYco+b3TCHOeZMB/q4xAvGTwQuIFIUJqqugKPyIZFQDA8vW5IaYoEtfB7SJigKDnhWEsWE76iLn/1q6tlo4t9YmhjCNXlxwqCW8zyGFe9T28XR37IFe74YWpEBcdUP2Jh8kH3UJF3IlBFElJLkctlPJ/cR12ciKbQ0U/QUBvR5GKmlVqAAUgtH4VBijbQdx3zRxWj6pZLKtgB55pQ3yNd3AHKLESyb7PIOQRXV48RZlD3fbxSO/Dt/dCw/x9VBV3TdsU86UAkZvZOHlnqNtNbJpSeKrRR1PNqFDgLu3mag8u37iy2+yg3AKny3TiTu+QKBgQD3ihF2lGvupLYoD27t+ceG8hdAU/VUKk4cG6J4IRNxVtuLljolTlUTnqOXrYVSlLRgCq8s/6P95qimqeimE/MaxXG+7FxtqlrUP0qmbJN0lrKtk/b27J0KFxjOCkc5jI6zoxy9SLkbJVEy5BHSOtirQPtrPf+YSHWnpGfWqZsb/QKBgQDiUGEn4fy/eVdKaYd9/fLqaFYzfCz0ResHEUiwrY+/8LtWOki/+40sycMPVUUKzReIQgbyyK+DRobJayGLKwLnAiwv4mrU5LEPwDrQEV9C8vDhKdftQ+MdmGDUcWyy8Hx2vHB+3f3HSWJSMgfJAFKLPkinaqiZl7qgOnfUebGnSwKBgQC8/Zjfo+nTxcWdSEs1PeLsUKEX6AICRFpmc2LTvM0PV3U/WW8DVI7378KO51hG+GnOednMzVDzZMuWk1qgkdKlueFiFytqPwohykDAV10tQnEgvTtswrITJhc2F/rRIVNCK2Kh0YgiBZe89wv0KiGKfo52Dlu6dHf/ijINXmiiMQKBgD7yBFPHczj+Ut0LhPfyDgJMis0/GHHIkJoioIkjfPvxxOkyxNFA8f8nDu3Q1acvbAAl/TDByK9Gim6zMV031VLTM98NppEYGnc7DCCa9Ff2ojC+JqSDYUP2AdiUw5rxcGzF9LQUomUvb/1UlP2+7TZ1WFfYPpSZsvqlyHZ8coEJAoGALlFIOCBe2eLlawvCoaJKNIu2SD+HnUEO3FI7npX7WVMaZFvAZgUN5bjVkV6MLyH+vnEqydpvqxh6dz+1S28E0v0QqsISeBjML9oUEE0zhRAIVsUKF3sMfT5kCrsXV/VSLKL9FQ3G5duFdMQvUxgB54iShjjYaO30r0fdgolkw2Q=";
    // 支付宝公钥
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmG1RLpvEijEPBY83uDE1sdpMA/aqT57IX8kbX540EWaY/8LtLpyV3ueZA3F4NxHao+nZ1UlzfJqKKEFqdP5DxOzYo3iN+u0z03jbyMtPW/5B+HmjojwrFQ8Kzkv/37k0HHh2dt0mVneEmUCyqRlno/08GMdn8OOrGy3k1LTP8Gxh40DckmCmObhARJhSVdPF8B74Cem8JU1bagxD4Hl/7lNhBT8AM2HT93zJjYpU9ueQoXwl/YCoiF981vgCjHETu+HW6uPwHtLyL2+MxF4Fr52qfwUxRFiSTN5T30VCe8Q/6PujILD/fVa1jkdMN5Dx26Y8x6BL09DaY33mIfvKpQIDAQAB";
    // 字符编码格式
    public String charset = "utf-8";
    //格式
    public String format = "json";
    // 签名方式
    public String signType = "RSA2";

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(serverUrl, aliAppId, privateKey,format, charset, publicKey,signType);
    }

}
