package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "车主登录接口", tags = "登录")
public class LoginController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登陆")
    public ErrorMsg complete(@ApiParam(value = "用户名", required = true)
                             @RequestParam(required = true) String userName,
                             @ApiParam(value = "密码", required = true)
                             @RequestParam(required = true) String password

    ) {
        CAccountInfo template = new CAccountInfo();
        template.setUserName(userName);
        template.setPassword(password);

        List<CAccountInfo> infos = accountService.getList(template, 0, 0);
        if (infos.size() == 0) {
            return new ErrorMsg(Error.LOGIN_PASSWORD_ERROR, "用户名密码错误");
        } else {
            return new ErrorMsg(Error.SUCCESS, "登陆成功", infos.get(0));
        }

    }
}
