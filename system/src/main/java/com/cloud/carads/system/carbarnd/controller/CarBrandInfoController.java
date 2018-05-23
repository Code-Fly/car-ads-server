package com.cloud.carads.system.carbarnd.controller;


import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.carbarnd.entity.CarBrandInfo;
import com.cloud.carads.system.carbarnd.service.ICarBrandInfoInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "车辆品牌信息", tags = "车辆品牌")
public class CarBrandInfoController {

    @Autowired
    private ICarBrandInfoInterface carBrandInfoInterface;

    @GetMapping(value = "/car/brandinfos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取车辆品牌或者型号信息列表")
    public ErrorMsg getList(@ApiParam(value = "品牌/型号code")
                            @RequestParam(required = false) String modelCode,
                            @ApiParam(value = "品牌/型号name，支持模糊查询")
                            @RequestParam(required = false) String modelName,
                            @ApiParam(value = "父节点的code")
                            @RequestParam(required = false) String parentCode) {
        CarBrandInfo tem = new CarBrandInfo();
        tem.setModelCode(modelCode);
        tem.setModelName(modelName);
        tem.setParentCode(parentCode);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), carBrandInfoInterface.getList(tem));

    }
}
