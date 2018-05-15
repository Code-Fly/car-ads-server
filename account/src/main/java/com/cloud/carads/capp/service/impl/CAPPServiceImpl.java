package com.cloud.carads.capp.service.impl;

import com.cloud.carads.capp.entity.CAppVersion;
import com.cloud.carads.capp.entity.CAppVersionExample;
import com.cloud.carads.capp.mapper.CAppVersionMapper;
import com.cloud.carads.capp.service.CAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CAPPServiceImpl implements CAppService {
    @Autowired
    CAppVersionMapper cappVersionMapper;
    @Override
    public List<CAppVersion> selectByExample(CAppVersionExample example) {
        return cappVersionMapper.selectByExample(example);
    }
}
