package com.hntyy.controller.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hntyy.bean.yeepay.query.MerProductFeeModifyParam;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParam;
import com.hntyy.bean.yeepay.query.RegisterSaasMerchantParamBackup;
import com.hntyy.bean.yeepay.query.RegisterSaasMicroParam;
import com.hntyy.bean.yeepay.result.QualUploadResult;
import com.hntyy.bean.yeepay.result.RegisterSaasMerchantResult;
import com.hntyy.service.yeepay.QualUploadService;
import com.hntyy.service.yeepay.RegisterSaasMerchantResultService;
import com.hntyy.service.yeepay.RegisterSaasMerchantService;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/yeepayController")
@Api(tags = "商户入网相关接口")
public class YeepayNetworkAccessController {

    @Autowired
    private QualUploadService qualUploadService;

    @Autowired
    private RegisterSaasMerchantService registerSaasMerchantService;

    @Autowired
    private RegisterSaasMerchantResultService registerSaasMerchantResultService;


    @ApiOperation(value="资质文件上传")
    @RequestMapping(value = "/qualUpload",method = RequestMethod.GET)
    public QualUploadResult qualUpload(@ApiParam(value = "文件上传url",required = true)@RequestParam(name = "fileUrl") String fileUrl,
                                       @ApiParam(value = "文件类型 1.本地文件全路径 2.远程文件url",required = true,allowableValues="1,2",defaultValue = "1")@RequestParam(name = "fileType") Integer fileType){
        String apiUri = "/yos/v1.0/sys/merchant/qual/upload";
        YopRequest request = new YopRequest();
        if (fileType.intValue() == 1){
            request.addFile("merQual",new File(fileUrl));
        } else {
            try {
                request.addFile("merQual",new URL(fileUrl).openStream());
            } catch (IOException e) {
                log.error("远程文件url错误！fileUrl:"+fileUrl);
                e.printStackTrace();
            }
        }
        try {
            YopResponse response = YopRsaClient.upload(apiUri, request);
            Map result = (Map) response.getResult();
            QualUploadResult qualUploadResult = new QualUploadResult();
            qualUploadResult.setFileUrl(fileUrl);
            qualUploadResult.setFileType(fileType);
            qualUploadResult.setMerQualUrl(result.get("merQualUrl")!=null?result.get("merQualUrl").toString():null);
            qualUploadResult.setReturnCode(result.get("returnCode")!=null?result.get("returnCode").toString():null);
            qualUploadResult.setReturnMsg(result.get("returnMsg")!=null?result.get("returnMsg").toString():null);
            if (!"REG00000".equals(result.get("returnCode"))){
                return qualUploadResult;
            }
            qualUploadService.insert(qualUploadResult);
            return qualUploadResult;
        } catch (IOException e) {
            log.error("子商户入网资质文件上传失败！fileUrl:"+fileUrl+",fileType:"+fileType);
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="特约商户入网(企业/个体)")
    @RequestMapping(value = "/registerSaasMerchant",method = RequestMethod.POST)
    public RegisterSaasMerchantResult registerSaasMerchant(@RequestBody RegisterSaasMerchantParam registerSaasMerchantParam){
        String apiUri = "/rest/v2.0/mer/register/saas/merchant";
        YopRequest request = new YopRequest();
        request.addParam("requestNo", registerSaasMerchantParam.getRequestNo());
        request.addParam("parentMerchantNo", registerSaasMerchantParam.getParentMerchantNo());
        request.addParam("businessRole", registerSaasMerchantParam.getBusinessRole());
        request.addParam("merchantSubjectInfo", JSON.toJSONString(registerSaasMerchantParam.getMerchantSubjectInfo()));
        request.addParam("merchantCorporationInfo", JSON.toJSONString(registerSaasMerchantParam.getMerchantCorporationInfo()));
        request.addParam("merchantContactInfo", JSON.toJSONString(registerSaasMerchantParam.getMerchantContactInfo()));
        request.addParam("businessAddressInfo", JSON.toJSONString(registerSaasMerchantParam.getBusinessAddressInfo()));
        request.addParam("settlementAccountInfo", JSON.toJSONString(registerSaasMerchantParam.getSettlementAccountInfo()));
        request.addParam("notifyUrl", registerSaasMerchantParam.getNotifyUrl());
        request.addParam("productInfo", JSONArray.toJSONString(registerSaasMerchantParam.getProductInfo()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
            registerSaasMerchantResult.setReturnCode(result.get("returnCode")!=null?result.get("returnCode").toString():null);
            registerSaasMerchantResult.setReturnMsg(result.get("returnMsg")!=null?result.get("returnMsg").toString():null);
            registerSaasMerchantResult.setRequestNo(result.get("requestNo")!=null?result.get("requestNo").toString():null);
            registerSaasMerchantResult.setApplicationNo(result.get("applicationNo")!=null?result.get("applicationNo").toString():null);
            registerSaasMerchantResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            registerSaasMerchantResult.setApplicationStatus(result.get("applicationStatus")!=null?result.get("applicationStatus").toString():null);
            // 请求成功保存数据
            if (!"NIG00000".equals(result.get("returnCode"))){
                return registerSaasMerchantResult;
            }
            // 存传参
            RegisterSaasMerchantParamBackup registerSaasMerchantParamBackup = new RegisterSaasMerchantParamBackup();
            BeanUtils.copyProperties(registerSaasMerchantParam,registerSaasMerchantParamBackup);
            registerSaasMerchantParamBackup.setSignName(registerSaasMerchantParam.getMerchantSubjectInfo().getSignName());
            registerSaasMerchantParamBackup.setLicenceNo(registerSaasMerchantParam.getMerchantSubjectInfo().getLicenceNo());
            registerSaasMerchantParamBackup.setMerchantSubjectInfoStr(registerSaasMerchantParam.getMerchantSubjectInfo().toString());
            registerSaasMerchantParamBackup.setMerchantCorporationInfoStr(registerSaasMerchantParam.getMerchantCorporationInfo().toString());
            registerSaasMerchantParamBackup.setMerchantContactInfoStr(registerSaasMerchantParam.getMerchantContactInfo().toString());
            registerSaasMerchantParamBackup.setBusinessAddressInfoStr(registerSaasMerchantParam.getBusinessAddressInfo().toString());
            registerSaasMerchantParamBackup.setSettlementAccountInfoStr(registerSaasMerchantParam.getSettlementAccountInfo().toString());
            registerSaasMerchantParamBackup.setProductInfoStr(registerSaasMerchantParam.getProductInfo().toString());
            registerSaasMerchantParamBackup.setType(1);
            registerSaasMerchantService.insert(registerSaasMerchantParamBackup);
            // 存返回结果
            registerSaasMerchantResult.setType(1);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
            return registerSaasMerchantResult;
        } catch (IOException e) {
            log.error("特约商户入网(企业/个体) 失败！param:{"+JSON.toJSONString(registerSaasMerchantParam)+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="特约商户入网(小微)")
    @RequestMapping(value = "/registerSaasMicro",method = RequestMethod.POST)
    public RegisterSaasMerchantResult registerSaasMicro(@RequestBody RegisterSaasMicroParam registerSaasMicroParam) {
        String apiUri = "/rest/v2.0/mer/register/saas/micro";
        YopRequest request = new YopRequest();
        request.addParam("requestNo", registerSaasMicroParam.getRequestNo());
        request.addParam("parentMerchantNo", registerSaasMicroParam.getParentMerchantNo());
        request.addParam("merchantSubjectInfo", JSON.toJSONString(registerSaasMicroParam.getMerchantSubjectInfo()));
        request.addParam("merchantCorporationInfo", JSON.toJSONString(registerSaasMicroParam.getMerchantCorporationInfo()));
        request.addParam("businessAddressInfo", JSON.toJSONString(registerSaasMicroParam.getBusinessAddressInfo()));
        request.addParam("accountInfo", JSON.toJSONString(registerSaasMicroParam.getAccountInfo()));
        request.addParam("notifyUrl", registerSaasMicroParam.getNotifyUrl());
        request.addParam("productInfo", JSONArray.toJSONString(registerSaasMicroParam.getProductInfo()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            RegisterSaasMerchantResult registerSaasMerchantResult = new RegisterSaasMerchantResult();
            registerSaasMerchantResult.setReturnCode(result.get("returnCode")!=null?result.get("returnCode").toString():null);
            registerSaasMerchantResult.setReturnMsg(result.get("returnMsg")!=null?result.get("returnMsg").toString():null);
            registerSaasMerchantResult.setRequestNo(result.get("requestNo")!=null?result.get("requestNo").toString():null);
            registerSaasMerchantResult.setApplicationNo(result.get("applicationNo")!=null?result.get("applicationNo").toString():null);
            registerSaasMerchantResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            registerSaasMerchantResult.setApplicationStatus(result.get("applicationStatus")!=null?result.get("applicationStatus").toString():null);
            // 请求成功保存数据
            if (!"NIG00000".equals(result.get("returnCode"))){
                return registerSaasMerchantResult;
            }
            // 存传参
            RegisterSaasMerchantParamBackup registerSaasMerchantParamBackup = new RegisterSaasMerchantParamBackup();
            BeanUtils.copyProperties(registerSaasMicroParam,registerSaasMerchantParamBackup);
            registerSaasMerchantParamBackup.setSignName(registerSaasMicroParam.getMerchantSubjectInfo().getSignName());
            registerSaasMerchantParamBackup.setLicenceNo(registerSaasMicroParam.getMerchantCorporationInfo().getLegalLicenceNo());
            registerSaasMerchantParamBackup.setMerchantSubjectInfoStr(registerSaasMicroParam.getMerchantSubjectInfo().toString());
            registerSaasMerchantParamBackup.setMerchantCorporationInfoStr(registerSaasMicroParam.getMerchantCorporationInfo().toString());
            registerSaasMerchantParamBackup.setBusinessAddressInfoStr(registerSaasMicroParam.getBusinessAddressInfo().toString());
            registerSaasMerchantParamBackup.setSettlementAccountInfoStr(registerSaasMicroParam.getAccountInfo().toString());
            registerSaasMerchantParamBackup.setProductInfoStr(registerSaasMicroParam.getProductInfo().toString());
            registerSaasMerchantParamBackup.setType(2);
            registerSaasMerchantService.insert(registerSaasMerchantParamBackup);
            // 存返回结果
            registerSaasMerchantResult.setType(2);
            registerSaasMerchantResultService.insert(registerSaasMerchantResult);
            return registerSaasMerchantResult;
        } catch (IOException e) {
            log.error("特约商户入网(小微) 失败！param:{"+JSON.toJSONString(registerSaasMicroParam)+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="商户入网进度查询")
    @RequestMapping(value = "/MerRegisterQuery",method = RequestMethod.GET)
    public Map MerRegisterQuery(@ApiParam(value = "入网请求号",required = true)@RequestParam(name = "requestNo") String requestNo) {
        String apiUri = "/rest/v2.0/mer/register/query";
        YopRequest request = new YopRequest();
        request.addParam("requestNo", requestNo);
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            return result;
        } catch (IOException e) {
            log.error("商户入网进度查询失败！param:{"+requestNo+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="商户产品费率变更")
    @RequestMapping(value = "/MerProductFeeModify",method = RequestMethod.POST)
    public RegisterSaasMerchantResult MerProductFeeModify(@RequestBody MerProductFeeModifyParam merProductFeeModifyParam) {
        String apiUri = "/rest/v2.0/mer/product/fee/modify";
        YopRequest request = new YopRequest();
        request.addParam("requestNo", merProductFeeModifyParam.getRequestNo());
        request.addParam("parentMerchantNo", merProductFeeModifyParam.getParentMerchantNo());
        request.addParam("merchantNo", merProductFeeModifyParam.getMerchantNo());
        request.addParam("notifyUrl", merProductFeeModifyParam.getNotifyUrl());
        request.addParam("productInfo", JSONArray.toJSONString(merProductFeeModifyParam.getProductInfo()));
        try {
            YopResponse response = YopRsaClient.post(apiUri, request);
            Map result = (Map) response.getResult();
            RegisterSaasMerchantResult merRegisterQueryResult = new RegisterSaasMerchantResult();
            merRegisterQueryResult.setReturnCode(result.get("returnCode")!=null?result.get("returnCode").toString():null);
            merRegisterQueryResult.setReturnMsg(result.get("returnMsg")!=null?result.get("returnMsg").toString():null);
            merRegisterQueryResult.setRequestNo(result.get("requestNo")!=null?result.get("requestNo").toString():null);
            merRegisterQueryResult.setApplicationNo(result.get("applicationNo")!=null?result.get("applicationNo").toString():null);
            merRegisterQueryResult.setMerchantNo(result.get("merchantNo")!=null?result.get("merchantNo").toString():null);
            merRegisterQueryResult.setApplicationStatus(result.get("applicationStatus")!=null?result.get("applicationStatus").toString():null);
            // 请求成功保存数据
            if (!"NIG00000".equals(result.get("returnCode"))){
                return merRegisterQueryResult;
            }
            // 存传参
            RegisterSaasMerchantParamBackup registerSaasMerchantParamBackup = new RegisterSaasMerchantParamBackup();
            registerSaasMerchantParamBackup.setRequestNo(merProductFeeModifyParam.getRequestNo());
            registerSaasMerchantParamBackup.setParentMerchantNo(merProductFeeModifyParam.getParentMerchantNo());
            registerSaasMerchantParamBackup.setMerchantNo(merProductFeeModifyParam.getMerchantNo());
            registerSaasMerchantParamBackup.setNotifyUrl(merProductFeeModifyParam.getNotifyUrl());
            registerSaasMerchantParamBackup.setProductInfoStr(merProductFeeModifyParam.getProductInfo().toString());
            registerSaasMerchantParamBackup.setType(3);
            registerSaasMerchantService.insert(registerSaasMerchantParamBackup);
            // 存返回结果
            merRegisterQueryResult.setType(3);
            registerSaasMerchantResultService.insert(merRegisterQueryResult);
            return merRegisterQueryResult;
        } catch (IOException e) {
            log.error("商户产品费率变更！param:{"+merProductFeeModifyParam+"}");
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="商户产品费率查询")
    @RequestMapping(value = "/MerProductFeeQuery",method = RequestMethod.GET)
    public Map MerProductFeeQuery(@ApiParam(value = "平台商商户编号",required = true)@RequestParam(name = "parentMerchantNo") String parentMerchantNo,
                                                         @ApiParam(value = "需要查询产品的商户编号",required = true)@RequestParam(name = "merchantNo") String merchantNo) {
        String apiUri = "/rest/v2.0/mer/product/fee/query";
        YopRequest request = new YopRequest();
        request.addParam("parentMerchantNo", parentMerchantNo);
        request.addParam("merchantNo", merchantNo);
        try {
            YopResponse response = YopRsaClient.get(apiUri, request);
            Map result = (Map) response.getResult();
            return result;
        } catch (IOException e) {
            log.error("商户入网进度查询失败！parentMerchantNo:"+parentMerchantNo+",merchantNo:"+merchantNo);
            e.printStackTrace();
        }
        return null;
    }

}
