package com.cloud.carads.system.dictionary.service.impl;

import com.cloud.carads.system.dictionary.entity.TdictionaryAttr;
import com.cloud.carads.system.dictionary.entity.TdictionaryAttrExample;
import com.cloud.carads.system.dictionary.mapper.TdictionaryAttrMapper;
import com.cloud.carads.system.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private TdictionaryAttrMapper tdictionaryAttrMapper;

    /**
     * 根据属性字段CODE查询所有属性和对应的值
     * @param dicCode
     * @return
     */
    @Override
    public List<TdictionaryAttr> queryDictionaryAttrByCode(String dicCode) {
        TdictionaryAttrExample example = new TdictionaryAttrExample();
        example.createCriteria().andDicCodeEqualTo(dicCode);
        return tdictionaryAttrMapper.selectByExample(example);
    }

    @Override
    public List<TdictionaryAttr> selectByExample(TdictionaryAttrExample example) {
        return tdictionaryAttrMapper.selectByExample(example);
    }
}
