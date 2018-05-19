package com.cloud.carads.system.capp.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.capp.entity.CAppVersion;
import com.cloud.carads.system.capp.entity.CAppVersionExample;
import com.cloud.carads.system.capp.service.CAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "车主app接口", tags = "车主app")
public class CAppController extends BaseController {
    @Autowired
    private CAppService cAppService;

    @GetMapping(value = "/capp/version/latest")
    @ApiOperation(value = "获取最新版本的车主app信息,返回的字段说明请看c_app_version表")
    public ErrorMsg getAPPVsersion(
            @ApiParam(value = "android,ios", required = true)
            @RequestParam(required = true) String os
    ) {
        CAppVersionExample example = new CAppVersionExample();
        CAppVersionExample.Criteria criteria = example.createCriteria();
        criteria.andOsEqualTo(os);
        example.setOrderByClause(" version_code desc limit 1");
        List<CAppVersion> versions = cAppService.selectByExample(example);
        return new ErrorMsg(Error.SUCCESS, "success", versions.get(0));
    }

}
