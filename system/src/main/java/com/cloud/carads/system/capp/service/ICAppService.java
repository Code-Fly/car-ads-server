package com.cloud.carads.system.capp.service;

import com.cloud.carads.system.capp.entity.CAppVersion;

import java.util.List;

public interface ICAppService {
    List<CAppVersion> getList(CAppVersion template, int rows, int page);

    List<CAppVersion> getLatest(CAppVersion template);

    long getListCount(CAppVersion template);

    int add(CAppVersion template);

    int update(CAppVersion template);

    int delete(List<Integer> ids);
}
