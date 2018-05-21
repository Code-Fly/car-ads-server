package com.cloud.carads.system.dictionary.service;

import com.cloud.carads.system.dictionary.entity.TdictionaryAttr;

import java.util.List;

public interface IDictionaryAttrService {
    List<TdictionaryAttr> getList(TdictionaryAttr template, int rows, int page);

    long getListCount(TdictionaryAttr template);

    int add(TdictionaryAttr template);

    int update(TdictionaryAttr template);

    int delete(List<Long> ids);
}
