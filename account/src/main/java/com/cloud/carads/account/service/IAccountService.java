/**
 *
 */
package com.cloud.carads.account.service;

import com.cloud.carads.account.entity.CAccountInfo;

import java.util.List;

/**
 * @author Barrie
 */
public interface IAccountService {
    List<CAccountInfo> getList(CAccountInfo template, int rows, int page);

    long getListCount(CAccountInfo template);

    int add(CAccountInfo template);

    int update(CAccountInfo template);

    int update(CAccountInfo conditions,CAccountInfo template);

    int delete(List<Long> ids);

    List<CAccountInfo> removePassword(List<CAccountInfo> list);

}
