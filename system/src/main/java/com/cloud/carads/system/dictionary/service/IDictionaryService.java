package com.cloud.carads.system.dictionary.service;

import com.cloud.carads.system.dictionary.entity.Tdictionary;

import java.util.List;

public interface IDictionaryService {
    List<Tdictionary> getList(Tdictionary template, int rows, int page);

    long getListCount(Tdictionary template);

    int add(Tdictionary template);

    int update(Tdictionary template);

    int delete(List<String> codes);
}
