/**
 *
 */
package com.cloud.carads.account.service;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoExample;

import java.util.List;

/**
 * @author Barrie
 */
public interface IUserService {
    int addCAccount(CAccountInfo cAccountInfo);
    int updateCAccountByID(CAccountInfo cAccountInfo);
    List<CAccountInfo> selectByExample(CAccountInfoExample example);
    CAccountInfo selectByPrimaryKey(Long id);

}
