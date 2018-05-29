package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.config.DefaultPasswordEncoder;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.utils.UUIDKeyGenerator;
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
@Api(description = "账户密码接口", tags = "密码")
public class PasswordController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private DefaultPasswordEncoder passwordEncoder;

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
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), user);
            }
        }
        return new ErrorMsg(Error.LOGIN_PASSWORD_ERROR.getValue(), Error.LOGIN_PASSWORD_ERROR.getReasonPhrase());
    }

    @PostMapping(value = "/user/password/change", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "重置密码")
    public ErrorMsg modifyPwd(
            @ApiParam(value = "手机号")
            @RequestParam(required = true) String phoneNo,
            @ApiParam(value = "新密码")
            @RequestParam(required = true) String newPwd,
            @ApiParam(value = "短信验证码")
            @RequestParam(required = true) String shortCode

    ) {
        // TODO 短信验证码校验
        CAccountInfo contions = new CAccountInfo();
        contions.setMobileNo(phoneNo);
        CAccountInfo template = new CAccountInfo();
        template.setPassword(passwordEncoder.encode(newPwd));
       int num =  accountService.update(contions,template);
       if(num == 0){
           return new ErrorMsg(Error.C_NOT_EXITS.getValue(), Error.C_NOT_EXITS.getReasonPhrase());
       }else{
           return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase());
       }

    }
}
