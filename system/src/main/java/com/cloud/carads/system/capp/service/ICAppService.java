package com.cloud.carads.system.capp.service;

import com.cloud.carads.system.capp.entity.CAppVersion;
import com.cloud.carads.system.capp.entity.CAppVersionExample;

import java.util.List;

public interface ICAppService {
    List<CAppVersion> selectByExample(CAppVersionExample example);
}
