package com.cloud.carads.management.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.management.entity.CAccountInfoDto;
import com.cloud.carads.management.service.IAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mobile/api")
public class MobileApiController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register(@ApiParam(value = "车主的基本信息")
                             @RequestBody(required = true) CAccountInfoDto accountInfo) {
        ErrorMsg errorMsg = accountService.register(accountInfo);
        return errorMsg;
    }
}
