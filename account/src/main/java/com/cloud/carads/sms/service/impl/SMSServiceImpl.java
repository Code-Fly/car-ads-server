package com.cloud.carads.sms.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cloud.carads.commons.utils.SMSUtil;
import com.cloud.carads.sms.entity.SmsLog;
import com.cloud.carads.sms.entity.SmsLogExample;
import com.cloud.carads.sms.mapper.SmsLogMapper;
import com.cloud.carads.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public int smsCode(String phoneNo, String fromIp) throws ClientException {
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
       /* SendSmsResponse response = SMSUtil.sendSms(phoneNo,String.valueOf(shortCode));
        record.setResponseTime(new Date());
        record.setStatus(response.getCode());
        logMapper.updateByPrimaryKey(record);
        if ("OK".equals(response.getCode())){
            return shortCode;
        }*/
        return shortCode;
    }


    /**
     * 查询该手机号最后一次发送的短信内容
     * @param phoneNo
     * @return
     */
    @Override
    public  SmsLog queryLastSMSByPhone(String phoneNo){
        SmsLogExample example = new SmsLogExample();
        SmsLogExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNoEqualTo(phoneNo);
        example.setOrderByClause("  receiver_time desc ");
        List<SmsLog> smsLogs = logMapper.selectByExample(example);
        if(smsLogs.size()>0){
            return smsLogs.get(0);
        }else{
            return new SmsLog();
        }

    }

}
