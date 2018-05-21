package com.cloud.carads.system.sms.service.impl;

import com.cloud.carads.system.sms.entity.SmsLog;
import com.cloud.carads.system.sms.entity.SmsLogExample;
import com.cloud.carads.system.sms.mapper.SmsLogMapper;
import com.cloud.carads.system.sms.service.ISMSService;
import com.rosegun.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMSServiceImpl implements ISMSService {
    @Autowired
    private SmsLogMapper logMapper;

    @Override
    public List<SmsLog> getList(SmsLog template, int rows, int page) {
        SmsLogExample condition = new SmsLogExample();
        SmsLogExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getPhoneNo()) {
            criteria.andPhoneNoEqualTo(template.getPhoneNo());
        }
        if (null != template && null != template.getFromIp()) {
            criteria.andFromIpEqualTo(template.getFromIp());
        }
        if (null != template && null != template.getChannel()) {
            criteria.andChannelEqualTo(template.getChannel());
        }
        if (null != template && null != template.getStatus()) {
            criteria.andStatusEqualTo(template.getStatus());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        condition.setOrderByClause("`update_time` ASC");
        return logMapper.selectByExample(condition);
    }

    @Override
    public List<SmsLog> getLatest(SmsLog template) {
        SmsLogExample condition = new SmsLogExample();
        SmsLogExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getPhoneNo()) {
            criteria.andPhoneNoEqualTo(template.getPhoneNo());
        }
        if (null != template && null != template.getFromIp()) {
            criteria.andFromIpEqualTo(template.getFromIp());
        }
        if (null != template && null != template.getChannel()) {
            criteria.andChannelEqualTo(template.getChannel());
        }
        if (null != template && null != template.getStatus()) {
            criteria.andStatusEqualTo(template.getStatus());
        }
        int page = 1;
        int rows = 1;
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        condition.setOrderByClause("`receiver_time` DESC");
        return logMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(SmsLog template) {
        SmsLogExample condition = new SmsLogExample();
        SmsLogExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getPhoneNo()) {
            criteria.andPhoneNoEqualTo(template.getPhoneNo());
        }
        if (null != template && null != template.getFromIp()) {
            criteria.andFromIpEqualTo(template.getFromIp());
        }
        if (null != template && null != template.getChannel()) {
            criteria.andChannelEqualTo(template.getChannel());
        }
        if (null != template && null != template.getStatus()) {
            criteria.andStatusEqualTo(template.getStatus());
        }

        return logMapper.countByExample(condition);
    }

    @Override
    public int add(SmsLog template) {
        return logMapper.insertSelective(template);
    }

    @Override
    public int update(SmsLog template) {
        SmsLogExample condition = new SmsLogExample();
        condition.or().andIdEqualTo(template.getId());
        return logMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<Integer> ids) {
        SmsLogExample condition = new SmsLogExample();
        condition.or().andIdIn(ids);
        return logMapper.deleteByExample(condition);
    }

}
