package com.hntyy.enums;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息通知枚举类
 */
public enum  NotifyUrlEnum {

    TYSHRW(1,"https://yeepay.mjjzxyh.com/notifyUrl/registerSaasMerchant","特约商户入网回调地址"),
    SHCPFLBG(2,"https://yeepay.mjjzxyh.com/notifyUrl/merProductFeeModify","商户产品费率变更"),
    ZFJG(3,"https://yeepay.mjjzxyh.com/notifyUrl/payResult","支付结果"),
    QSCG(4,"https://yeepay.mjjzxyh.com/notifyUrl/reckoningSuccess","清算成功"),
    SQTK(5,"https://yeepay.mjjzxyh.com/notifyUrl/tradeRefund","申请退款");

    private final int key;
    private final String value;
    private final String desc;

    NotifyUrlEnum(int key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static  String getValueByKey(Integer key){
        NotifyUrlEnum[] notifyUrlEnums = values();
        for (NotifyUrlEnum notifyUrlEnum : notifyUrlEnums) {
            if (notifyUrlEnum.getKey()== key) {
                return notifyUrlEnum.getValue();
            }
        }
        return null;
    }

}
