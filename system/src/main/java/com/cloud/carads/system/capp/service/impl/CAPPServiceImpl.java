package com.cloud.carads.system.capp.service.impl;

import com.cloud.carads.system.capp.entity.CAppVersion;
import com.cloud.carads.system.capp.entity.CAppVersionExample;
import com.cloud.carads.system.capp.mapper.CAppVersionMapper;
import com.cloud.carads.system.capp.service.ICAppService;
import com.rosegun.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CAPPServiceImpl implements ICAppService {
    @Autowired
    CAppVersionMapper cappVersionMapper;

    @Override
    public List<CAppVersion> getList(CAppVersion template, int rows, int page) {
        CAppVersionExample condition = new CAppVersionExample();
        CAppVersionExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getVersionCode()) {
            criteria.andVersionCodeEqualTo(template.getVersionCode());
        }
        if (null != template && null != template.getVersionName()) {
            criteria.andVersionNameEqualTo(template.getVersionName());
        }
        if (null != template && null != template.getUpdateTime()) {
            criteria.andUpdateTimeEqualTo(template.getUpdateTime());
        }
        if (null != template && null != template.getAppSize()) {
            criteria.andAppSizeEqualTo(template.getAppSize());
        }
        if (null != template && null != template.getStrongUpt()) {
            criteria.andStrongUptEqualTo(template.getStrongUpt());
        }
        if (null != template && null != template.getOs()) {
            criteria.andOsEqualTo(template.getOs());
        }
        if (null != template && null != template.getDownloadUrl()) {
            criteria.andDownloadUrlEqualTo(template.getDownloadUrl());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        condition.setOrderByClause("`update_time` ASC");
        return cappVersionMapper.selectByExample(condition);
    }

    @Override
    public List<CAppVersion> getLatest(CAppVersion template) {
        CAppVersionExample condition = new CAppVersionExample();
        CAppVersionExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getVersionCode()) {
            criteria.andVersionCodeEqualTo(template.getVersionCode());
        }
        if (null != template && null != template.getVersionName()) {
            criteria.andVersionNameEqualTo(template.getVersionName());
        }
        if (null != template && null != template.getUpdateTime()) {
            criteria.andUpdateTimeEqualTo(template.getUpdateTime());
        }
        if (null != template && null != template.getAppSize()) {
            criteria.andAppSizeEqualTo(template.getAppSize());
        }
        if (null != template && null != template.getStrongUpt()) {
            criteria.andStrongUptEqualTo(template.getStrongUpt());
        }
        if (null != template && null != template.getOs()) {
            criteria.andOsEqualTo(template.getOs());
        }
        if (null != template && null != template.getDownloadUrl()) {
            criteria.andDownloadUrlEqualTo(template.getDownloadUrl());
        }
        int page = 1;
        int rows = 1;
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        condition.setOrderByClause("`version_code` ASC");
        return cappVersionMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(CAppVersion template) {
        CAppVersionExample condition = new CAppVersionExample();
        CAppVersionExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getVersionCode()) {
            criteria.andVersionCodeEqualTo(template.getVersionCode());
        }
        if (null != template && null != template.getVersionName()) {
            criteria.andVersionNameEqualTo(template.getVersionName());
        }
        if (null != template && null != template.getUpdateTime()) {
            criteria.andUpdateTimeEqualTo(template.getUpdateTime());
        }
        if (null != template && null != template.getAppSize()) {
            criteria.andAppSizeEqualTo(template.getAppSize());
        }
        if (null != template && null != template.getStrongUpt()) {
            criteria.andStrongUptEqualTo(template.getStrongUpt());
        }
        if (null != template && null != template.getOs()) {
            criteria.andOsEqualTo(template.getOs());
        }
        if (null != template && null != template.getDownloadUrl()) {
            criteria.andDownloadUrlEqualTo(template.getDownloadUrl());
        }

        return cappVersionMapper.countByExample(condition);
    }

    @Override
    public int add(CAppVersion template) {
        return cappVersionMapper.insertSelective(template);
    }

    @Override
    public int update(CAppVersion template) {
        CAppVersionExample condition = new CAppVersionExample();
        condition.or().andIdEqualTo(template.getId());
        return cappVersionMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<Integer> ids) {
        CAppVersionExample condition = new CAppVersionExample();
        condition.or().andIdIn(ids);
        return cappVersionMapper.deleteByExample(condition);
    }
}
