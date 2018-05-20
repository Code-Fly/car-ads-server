package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.utils.UUIDKeyGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "账户密码接口", tags = "密码")
public class PasswordController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/user/password/validate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "校验密码")
    public ErrorMsg validate(@ApiParam(value = "用户名", required = true)
                             @RequestParam(required = true) String userName,
                             @ApiParam(value = "密码", required = true)
                             @RequestParam(required = true) String password

    ) {
        CAccountInfo template = new CAccountInfo();
        template.setUserName(userName);
//        template.setPassword(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5+password,"UTF-8"));
//        template.setPassword(new StandardPasswordEncoder().encode(password));
        List<CAccountInfo> users = accountService.getList(template, 0, 0);
        if (users.size() == 1) {
            CAccountInfo user = users.get(0);
            if (new StandardPasswordEncoder().matches(password, user.getPassword())) {
                return new ErrorMsg(Error.SUCCESS, "success", user);
            }
        }
        return new ErrorMsg(Error.LOGIN_PASSWORD_ERROR, "Username or password incorrect");
    }

    @PostMapping(value = "/user/password/reset", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "重置密码")
    public ErrorMsg reset(@ApiParam(value = "用户名", required = true)
                          @RequestParam(required = true) String userName

    ) {
        String password = UUIDKeyGenerator.getUUID();

        CAccountInfo template = new CAccountInfo();
        template.setUserName(userName);
        List<CAccountInfo> users = accountService.getList(template, 0, 0);
        if (users.size() == 1) {
            CAccountInfo user = users.get(0);
            template.setId(user.getId());
            template.setPassword(new StandardPasswordEncoder().encode(password));
            accountService.update(template);
            return new ErrorMsg(Error.SUCCESS, "success", password);
        }
        return new ErrorMsg(Error.LOGIN_PASSWORD_ERROR, "Username or password incorrect");
    }
}
