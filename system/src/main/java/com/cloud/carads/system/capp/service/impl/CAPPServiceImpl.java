package com.cloud.carads.system.capp.service.impl;

import com.cloud.carads.system.capp.entity.CAppVersion;
import com.cloud.carads.system.capp.entity.CAppVersionExample;
import com.cloud.carads.system.capp.mapper.CAppVersionMapper;
import com.cloud.carads.system.capp.service.ICAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CAPPServiceImpl implements ICAppService {
    @Autowired
    CAppVersionMapper cappVersionMapper;

    @Override
    public List<CAppVersion> selectByExample(CAppVersionExample example) {
        return cappVersionMapper.selectByExample(example);
    }
}
