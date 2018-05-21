package com.cloud.carads.system.area.service;

import com.cloud.carads.system.area.entity.TAreainfo;

import java.util.List;

public interface IAreaService {
    List<TAreainfo> getList(TAreainfo template, int rows, int page);

    long getListCount(TAreainfo template);

    int add(TAreainfo template);

    int update(TAreainfo template);

    int delete(List<Integer> ids);
}

