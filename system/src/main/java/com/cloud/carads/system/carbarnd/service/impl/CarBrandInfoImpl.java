package com.cloud.carads.system.carbarnd.service.impl;

import com.cloud.carads.system.carbarnd.entity.CarBrandInfo;
import com.cloud.carads.system.carbarnd.entity.CarBrandInfoExample;
import com.cloud.carads.system.carbarnd.mapper.CarBrandInfoMapper;
import com.cloud.carads.system.carbarnd.service.ICarBrandInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandInfoImpl implements ICarBrandInfo {

    @Autowired
    private CarBrandInfoMapper carBrandInfoMapper;

    @Override
    public List<CarBrandInfo> getList(CarBrandInfo template) {
        CarBrandInfoExample example = new CarBrandInfoExample();
        CarBrandInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(template.getParentCode())) {
            criteria.andParentCodeEqualTo(template.getParentCode());
        }
        if (StringUtils.isNotEmpty(template.getModelCode())) {
            criteria.andModelCodeEqualTo(template.getParentCode());
        }
        if (StringUtils.isNotEmpty(template.getModelName())) {
            criteria.andModelNameLike("%" + template.getModelName() + "%");
        }
        criteria.andFlagEqualTo(1);
        carBrandInfoMapper.selectByExample(example);
        return null;
    }
}
