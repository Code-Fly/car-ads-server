package com.cloud.carads.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.carads.sms.entity.SmsLog;

public interface SMSService {
    int smsCode(String phoneNo, String fromIp) throws ClientException;

    SmsLog queryLastSMSByPhone(String phoneNo);
}
