/**
 *
 */
package com.cloud.carads.account.service.impl;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoExample;
import com.cloud.carads.account.mapper.CAccountInfoMapper;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.service.BaseService;
import com.rosegun.plugin.Page;
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
    public List<CAccountInfo> getList(CAccountInfo template, int rows, int page) {
        CAccountInfoExample condition = new CAccountInfoExample();
        CAccountInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getFatherId()) {
            criteria.andFatherIdEqualTo(template.getFatherId());
        }
        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
        }
        if (null != template && null != template.getGrandId()) {
            criteria.andGrandIdEqualTo(template.getGrandId());
        }
        if (null != template && null != template.getGgrandId()) {
            criteria.andGgrandIdEqualTo(template.getGgrandId());
        }
        if (null != template && null != template.getMobileNo()) {
            criteria.andMobileNoEqualTo(template.getMobileNo());
        }
        if (null != template && null != template.getIdentityNo()) {
            criteria.andIdentityNoEqualTo(template.getIdentityNo());
        }
        if (null != template && null != template.getPlateNo()) {
            criteria.andPlateNoEqualTo(template.getPlateNo());
        }
        if (null != template && null != template.getBankCarNo()) {
            criteria.andBankCarNoEqualTo(template.getBankCarNo());
        }
        if (null != template && null != template.getNetCar()) {
            criteria.andNetCarEqualTo(template.getNetCar());
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }
        if (rows > 0 && page > 0) {
            condition.setPage(new Page((page - 1) * rows, rows));
        }
        condition.setOrderByClause("`create_time` ASC");
        return cAccountInfoMapper.selectByExample(condition);
    }

    @Override
    public long getListCount(CAccountInfo template) {
        CAccountInfoExample condition = new CAccountInfoExample();
        CAccountInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getFatherId()) {
            criteria.andFatherIdEqualTo(template.getFatherId());
        }
        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
        }
        if (null != template && null != template.getGrandId()) {
            criteria.andGrandIdEqualTo(template.getGrandId());
        }
        if (null != template && null != template.getGgrandId()) {
            criteria.andGgrandIdEqualTo(template.getGgrandId());
        }
        if (null != template && null != template.getMobileNo()) {
            criteria.andMobileNoEqualTo(template.getMobileNo());
        }
        if (null != template && null != template.getIdentityNo()) {
            criteria.andIdentityNoEqualTo(template.getIdentityNo());
        }
        if (null != template && null != template.getPlateNo()) {
            criteria.andPlateNoEqualTo(template.getPlateNo());
        }
        if (null != template && null != template.getBankCarNo()) {
            criteria.andBankCarNoEqualTo(template.getBankCarNo());
        }
        if (null != template && null != template.getNetCar()) {
            criteria.andNetCarEqualTo(template.getNetCar());
        }
        if (null != template && null != template.getFlag()) {
            criteria.andFlagEqualTo(template.getFlag());
        }

        return cAccountInfoMapper.countByExample(condition);
    }

    @Override
    public int add(CAccountInfo template) {
        return cAccountInfoMapper.insertSelective(template);
    }

    @Override
    public int update(CAccountInfo template) {
        CAccountInfoExample condition = new CAccountInfoExample();
        condition.or().andIdEqualTo(template.getId());
        return cAccountInfoMapper.updateByExampleSelective(template, condition);
    }

    @Override
    public int delete(List<Long> ids) {
        CAccountInfoExample condition = new CAccountInfoExample();
        condition.or().andIdIn(ids);
        return cAccountInfoMapper.deleteByExample(condition);
    }

    @Override
    public List<CAccountInfo> removePassword(List<CAccountInfo> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPassword("******");
        }
        return list;
    }
}
