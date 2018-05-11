/**
 *
 */
package com.cloud.carads.account.service.impl;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.mapper.CAccountInfoMapper;
import com.cloud.carads.account.service.IUserService;
import com.cloud.carads.commons.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Barrie
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {
 @Autowired
 private CAccountInfoMapper accountInfoMapper;


    @Override
    public int addCAccount(CAccountInfo cAccountInfo) {
        return accountInfoMapper.insertSelective(cAccountInfo);
    }

    @Override
    public int updateCAccountByID(CAccountInfo cAccountInfo) {
        return accountInfoMapper.updateByPrimaryKey(cAccountInfo);
    }
}
