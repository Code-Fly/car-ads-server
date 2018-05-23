package com.cloud.carads.system.carbarnd.service;

import com.cloud.carads.system.carbarnd.entity.CarBrandInfo;

import java.util.List;

public interface ICarBrandInfo {
    List<CarBrandInfo> getList(CarBrandInfo template);
}
