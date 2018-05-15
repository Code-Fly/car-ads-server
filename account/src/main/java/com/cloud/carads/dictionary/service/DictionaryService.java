package com.cloud.carads.dictionary.service;

import com.cloud.carads.dictionary.entity.TdictionaryAttr;
import com.cloud.carads.dictionary.entity.TdictionaryAttrExample;

import java.util.List;

public interface DictionaryService {
    List<TdictionaryAttr> queryDictionaryAttrByCode(String dicCode);
    List<TdictionaryAttr> selectByExample(TdictionaryAttrExample example);

}
