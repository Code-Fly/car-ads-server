package com.cloud.carads.capp.service;

import com.cloud.carads.capp.entity.CAppVersion;
import com.cloud.carads.capp.entity.CAppVersionExample;

import java.util.List;

public interface CAppService {
    List<CAppVersion> selectByExample(CAppVersionExample example);
}
