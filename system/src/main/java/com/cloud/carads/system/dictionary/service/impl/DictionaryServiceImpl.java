package com.cloud.carads.system.dictionary.service.impl;

import com.cloud.carads.system.dictionary.entity.Tdictionary;
import com.cloud.carads.system.dictionary.entity.TdictionaryExample;
import com.cloud.carads.system.dictionary.mapper.TdictionaryMapper;
import com.cloud.carads.system.dictionary.service.IDictionaryService;
import com.rosegun.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private TdictionaryMapper tdictionaryMapper;

    @Override
    public List<Tdictionary> getList(Tdictionary template, int rows, int page) {
        TdictionaryExample condition = new TdictionaryExample();
        TdictionaryExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getDicCode()) {
            criteria.andDicCodeEqualTo(template.getDicCode());
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        return tdictionaryMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(Tdictionary template) {
        TdictionaryExample condition = new TdictionaryExample();
        TdictionaryExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getDicCode()) {
            criteria.andDicCodeEqualTo(template.getDicCode());
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }

        return tdictionaryMapper.countByExample(condition);
    }

    @Override
    public int add(Tdictionary template) {
        return tdictionaryMapper.insertSelective(template);
    }

    @Override
    public int update(Tdictionary template) {
        TdictionaryExample condition = new TdictionaryExample();
        condition.or().andDicCodeEqualTo(template.getDicCode());
        return tdictionaryMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<String> codes) {
        TdictionaryExample condition = new TdictionaryExample();
        condition.or().andDicCodeIn(codes);
        return tdictionaryMapper.deleteByExample(condition);
    }
}
