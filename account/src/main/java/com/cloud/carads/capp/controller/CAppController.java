package com.cloud.carads.capp.controller;

import com.cloud.carads.capp.entity.CAppVersion;
import com.cloud.carads.capp.entity.CAppVersionExample;
import com.cloud.carads.capp.service.CAppService;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/capp")
@Api(description = "数据字典接口")
public class CAppController{
    @Autowired
    private CAppService cAppService;
    @GetMapping(value = "/version/latest")
    @ApiOperation(value = "获取最新版本的车主app信息,返回的字段说明请看c_app_version表")
    public ErrorMsg getAPPVsersion(
            @ApiParam(value = "android,ios")
            @RequestParam(required = true) String os
                                               ){
        CAppVersionExample example = new CAppVersionExample();
        CAppVersionExample.Criteria  criteria=    example.createCriteria();
        criteria.andOsEqualTo(os);
        example.setOrderByClause(" version_code desc limit 1");
        List<CAppVersion>  versions = cAppService.selectByExample(example);
        return new ErrorMsg(Error.SUCCESS, "success", versions.get(0));
    }

}
