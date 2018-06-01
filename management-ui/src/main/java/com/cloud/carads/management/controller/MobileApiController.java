package com.cloud.carads.management.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.management.entity.CAccountInfoDto;
import com.cloud.carads.management.service.IAccountService;
import com.cloud.carads.management.service.ISMSService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mobile/api")
public class MobileApiController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISMSService smsService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register(@ApiParam(value = "车主的基本信息")
                             @RequestBody(required = true) CAccountInfoDto accountInfo) {
        ErrorMsg errorMsg = accountService.register(accountInfo);
        return errorMsg;
    }

    @GetMapping(value = "/smss/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送验证码")
    public ErrorMsg getCAccountInfo(
            @ApiParam(value = "发送手机号码")
            @RequestParam(required = true) String phoneNo
    ) {
        return smsService.sendShortCode(phoneNo);
    }
}
