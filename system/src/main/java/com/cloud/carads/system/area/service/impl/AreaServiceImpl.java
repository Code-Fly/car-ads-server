package com.cloud.carads.system.area.service.impl;

import com.cloud.carads.system.area.entity.TAreainfo;
import com.cloud.carads.system.area.entity.TAreainfoExample;
import com.cloud.carads.system.area.mapper.TAreainfoMapper;
import com.cloud.carads.system.area.service.IAreaService;
import com.rosegun.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private TAreainfoMapper areainfoMapper;

    @Override
    public List<TAreainfo> getList(TAreainfo template, int rows, int page) {
        TAreainfoExample condition = new TAreainfoExample();
        TAreainfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getParentId()) {
            criteria.andParentIdEqualTo(template.getParentId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameEqualTo(template.getName());
        }
        if (null != template && null != template.getArealevel()) {
            criteria.andArealevelEqualTo(template.getArealevel());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        return areainfoMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(TAreainfo template) {
        TAreainfoExample condition = new TAreainfoExample();
        TAreainfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getParentId()) {
            criteria.andParentIdEqualTo(template.getParentId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameEqualTo(template.getName());
        }
        if (null != template && null != template.getArealevel()) {
            criteria.andArealevelEqualTo(template.getArealevel());
        }

        return areainfoMapper.countByExample(condition);
    }

    @Override
    public int add(TAreainfo template) {
        return areainfoMapper.insertSelective(template);
    }

    @Override
    public int update(TAreainfo template) {
        TAreainfoExample condition = new TAreainfoExample();
        condition.or().andIdEqualTo(template.getId());
        return areainfoMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<Integer> ids) {
        TAreainfoExample condition = new TAreainfoExample();
        condition.or().andIdIn(ids);
        return areainfoMapper.deleteByExample(condition);
    }
}
