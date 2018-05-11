package com.cloud.carads.area.service.impl;

import com.cloud.carads.area.entity.TAreainfo;
import com.cloud.carads.area.entity.TAreainfoExample;
import com.cloud.carads.area.mapper.TAreainfoMapper;
import com.cloud.carads.area.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private TAreainfoMapper areainfoMapper;
    @Override
    public List<TAreainfo> selectByExample(TAreainfoExample example) {
        return areainfoMapper.selectByExample(example);
    }

}
