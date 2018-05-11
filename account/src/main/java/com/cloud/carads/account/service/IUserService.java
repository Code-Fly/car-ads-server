/**
 *
 */
package com.cloud.carads.account.service;

import com.cloud.carads.account.entity.CAccountInfo;

/**
 * @author Barrie
 */
public interface IUserService {
    int addCAccount(CAccountInfo cAccountInfo);
    int updateCAccountByID(CAccountInfo cAccountInfo);

}
