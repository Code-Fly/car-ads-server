/**
 *
 */
package com.cloud.carads.account.service.impl;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoExample;
import com.cloud.carads.account.mapper.CAccountInfoMapper;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.service.BaseService;
import com.cloud.carads.commons.utils.MD5Util;
import com.cloud.carads.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Barrie
 */
@Service
public class AccountServiceImpl extends BaseService implements IAccountService {

 @Autowired
 private CAccountInfoMapper cAccountInfoMapper;



    @Override
    public int addCAccount(CAccountInfo cAccountInfo) {
        cAccountInfo.setPassword(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5+cAccountInfo.getPassword(),"UTF-8"));
        return cAccountInfoMapper.insertSelective(cAccountInfo);
    }

    @Override
    public int updateCAccountByID(CAccountInfo cAccountInfo) {
        return cAccountInfoMapper.updateByPrimaryKey(cAccountInfo);

    }

    @Override
    public List<CAccountInfo> selectByExample(CAccountInfoExample example) {
        return cAccountInfoMapper.selectByExample(example);
    }

    @Override
    public CAccountInfo selectByPrimaryKey(Long id) {
        return cAccountInfoMapper.selectByPrimaryKey(id);
    }


}
