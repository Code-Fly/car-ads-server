package com.cloud.carads.system.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.carads.system.sms.entity.SmsLog;

public interface ISMSService {
    int smsCode(String phoneNo, String fromIp) throws ClientException;

    SmsLog queryLastSMSByPhone(String phoneNo);
}
