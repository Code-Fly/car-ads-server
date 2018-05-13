package com.cloud.carads.sms.service;

import com.aliyuncs.exceptions.ClientException;

public interface SMSService {
    int smsCode(String phoneNo, String fromIp) throws ClientException;
}
