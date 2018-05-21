package com.cloud.carads.system.sms.service;

import com.cloud.carads.system.sms.entity.SmsLog;

import java.util.List;

public interface ISMSService {
    List<SmsLog> getList(SmsLog template, int rows, int page);

    List<SmsLog> getLatest(SmsLog template);

    long getListCount(SmsLog template);

    int add(SmsLog template);

    int update(SmsLog template);

    int delete(List<Integer> ids);
}
