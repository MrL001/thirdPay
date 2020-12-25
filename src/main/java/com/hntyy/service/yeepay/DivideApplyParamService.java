package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.DivideApplyParam;
import com.hntyy.bean.yeepay.query.DivideApplyParamBackup;

/**
 * 申请分账参数
 */
public interface DivideApplyParamService {

    void insert(DivideApplyParamBackup divideApplyParamBackup);

}
