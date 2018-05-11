/**
 *
 */
package com.cloud.carads.account.service.impl;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.mapper.CAccountInfoMapper;
import com.cloud.carads.account.service.IUserService;
import com.cloud.carads.commons.service.BaseService;
import com.cloud.carads.commons.utils.MD5Util;
import com.cloud.carads.constant.SystemConstant;
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
        cAccountInfo.setPassword(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5+cAccountInfo.getPassword(),"UTF-8"));
        return accountInfoMapper.insertSelective(cAccountInfo);
    }

    @Override
    public int updateCAccountByID(CAccountInfo cAccountInfo) {
        return accountInfoMapper.updateByPrimaryKey(cAccountInfo);
    }
}
