package com.cloud.carads.area.service;

import com.cloud.carads.area.entity.TAreainfo;
import com.cloud.carads.area.entity.TAreainfoExample;

import java.util.List;

public interface IAreaService {
    List<TAreainfo> selectByExample(TAreainfoExample example);
}

