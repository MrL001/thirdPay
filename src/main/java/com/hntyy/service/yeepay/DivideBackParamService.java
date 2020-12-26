package com.hntyy.service.yeepay;

import com.hntyy.bean.yeepay.query.DivideBackParam;
import com.hntyy.bean.yeepay.query.DivideBackParamBackup;

/**
 * 申请分账资金归还参数
 */
public interface DivideBackParamService {

    void insert(DivideBackParamBackup divideBackParamBackup);

}
