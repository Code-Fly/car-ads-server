package com.cloud.carads.system.area.service;

import com.cloud.carads.system.area.entity.TAreainfo;
import com.cloud.carads.system.area.entity.TAreainfoExample;

import java.util.List;

public interface IAreaService {
    List<TAreainfo> selectByExample(TAreainfoExample example);
}

