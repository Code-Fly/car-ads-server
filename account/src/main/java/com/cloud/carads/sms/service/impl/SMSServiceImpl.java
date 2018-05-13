package com.cloud.carads.sms.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cloud.carads.commons.utils.SMSUtil;
import com.cloud.carads.sms.entity.SmsLog;
import com.cloud.carads.sms.mapper.SmsLogMapper;
import com.cloud.carads.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SMSServiceImpl implements SMSService {
    @Autowired
    private SmsLogMapper logMapper;

    /**
     * 返回成功
     * @param phoneNo
     * @param fromIp
     * @return
     * @throws ClientException
     */
    @Override
    public int smsCode(String phoneNo,String fromIp) throws ClientException {
        /**
         * 生成六位数的随机验证码
         */
         int shortCode = (int)((Math.random()*9+1)*100000);
        SmsLog record = new SmsLog();
        record.setPhoneNo(phoneNo);
        record.setChannel("aliyun");
        record.setFromIp(fromIp);
        record.setContent(String.valueOf(shortCode));

        // 插入发送记录 返回logid
        logMapper.insertSelective(record);
        int id = record.getId();
        SendSmsResponse response = SMSUtil.sendSms(phoneNo,String.valueOf(shortCode));
        record.setResponseTime(new Date());
        record.setStatus(response.getCode());
        logMapper.updateByPrimaryKey(record);
        if ("OK".equals(response.getCode())){
            return shortCode;
        }
        return 0;
    }

}
