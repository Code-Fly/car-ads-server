package com.cloud.carads.system.dictionary.service.impl;

import com.cloud.carads.system.dictionary.entity.TdictionaryAttr;
import com.cloud.carads.system.dictionary.entity.TdictionaryAttrExample;
import com.cloud.carads.system.dictionary.mapper.TdictionaryAttrMapper;
import com.cloud.carads.system.dictionary.service.IDictionaryAttrService;
import com.rosegun.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryAttrServiceImpl implements IDictionaryAttrService {
    @Autowired
    private TdictionaryAttrMapper tdictionaryAttrMapper;

    @Override
    public List<TdictionaryAttr> getList(TdictionaryAttr template, int rows, int page) {
        TdictionaryAttrExample condition = new TdictionaryAttrExample();
        TdictionaryAttrExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getDicCode()) {
            criteria.andDicCodeEqualTo(template.getDicCode());
        }
        if (null != template && null != template.getAttrCode()) {
            criteria.andAttrCodeEqualTo(template.getAttrCode());
        }
        if (null != template && null != template.getAttrValue()) {
            criteria.andAttrValueLike("%" + template.getAttrValue() + "%");
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        return tdictionaryAttrMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(TdictionaryAttr template) {
        TdictionaryAttrExample condition = new TdictionaryAttrExample();
        TdictionaryAttrExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getDicCode()) {
            criteria.andDicCodeEqualTo(template.getDicCode());
        }
        if (null != template && null != template.getAttrCode()) {
            criteria.andAttrCodeEqualTo(template.getAttrCode());
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }

        return tdictionaryAttrMapper.countByExample(condition);
    }

    @Override
    public int add(TdictionaryAttr template) {
        return tdictionaryAttrMapper.insertSelective(template);
    }

    @Override
    public int update(TdictionaryAttr template) {
        TdictionaryAttrExample condition = new TdictionaryAttrExample();
        condition.or().andIdEqualTo(template.getId());
        return tdictionaryAttrMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<Long> ids) {
        TdictionaryAttrExample condition = new TdictionaryAttrExample();
        condition.or().andIdIn(ids);
        return tdictionaryAttrMapper.deleteByExample(condition);
    }
}
