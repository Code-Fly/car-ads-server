package com.cloud.carads.system.dictionary.service;

import com.cloud.carads.system.dictionary.entity.TdictionaryAttr;
import com.cloud.carads.system.dictionary.entity.TdictionaryAttrExample;

import java.util.List;

public interface IDictionaryService {
    List<TdictionaryAttr> queryDictionaryAttrByCode(String dicCode);
    List<TdictionaryAttr> selectByExample(TdictionaryAttrExample example);

}
