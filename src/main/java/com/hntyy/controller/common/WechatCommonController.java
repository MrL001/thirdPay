package com.hntyy.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.hntyy.common.WeChatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/wechatCommon")
@Api(tags = "微信工具类")
public class WechatCommonController {

    private String wxAppId = "wx5f0e254d6e3749a2";

    private String secret = "f32c62c1889f1b16b24720dac4c944be";


    @ApiOperation(value = "获取openId")
    @RequestMapping(value = "/getWxOpenId", method = RequestMethod.GET)
    public String getWxOpenId(@ApiParam(value = "code", required = true) @RequestParam(name = "code") String code) throws Exception {
        log.info("---------------code:"+code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + wxAppId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        log.info("------------------ str:"+str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        String openid = jsonObject.get("openid").toString();
        return openid;
    }

    private static String interfaceUtil(String path, String data) {
        String openId = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                openId = str;
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            //System.out.println("完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openId;
    }
}
