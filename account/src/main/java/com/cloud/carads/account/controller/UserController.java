/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoDto;
import com.cloud.carads.account.entity.CAccountInfoExample;
import com.cloud.carads.account.service.IUserService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.exception.ConnectionFailedException;
import com.cloud.carads.commons.utils.MD5Util;
import com.cloud.carads.constant.SystemConstant;
import com.cloud.carads.sms.entity.SmsLog;
import com.cloud.carads.sms.service.SMSService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 */
@RestController
@RequestMapping(value = "/account")
@Api(description = "车主管理接口")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private SMSService smsService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id获取车主基本信息")
    public ErrorMsg getCAccountInfo(@ApiParam(value = "车主的id")
                                    @RequestParam(required = true)Long id){
        return new ErrorMsg(Error.SUCCESS, "success",userService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register (@ApiParam(value = "车主的基本信息")
                                      @RequestBody(required = true)CAccountInfoDto accountInfo
                              ) {
        // 校验验证码
        SmsLog smsLog = smsService.queryLastSMSByPhone(accountInfo.getMobileNo());
        //ToDO
       /* if (!accountInfo.getShortCode().equals(smsLog.getContent())){
            return new ErrorMsg(Error.SMS_SHORTCODE_ERROR, "验证码错误。");
        }*/
        // 草稿未完善信息
        accountInfo.setFlag(9);
        userService.addCAccount(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success",accountInfo.getId());
    }

    @PostMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主完善信息")
    public ErrorMsg complete (@ApiParam(value = "车主的全部信息")
                              @RequestParam(required = true)CAccountInfo info
    ) {
        info.setUpdateTime(new Date());
        // 待审核
        info.setFlag(0);
        userService.updateCAccountByID(info);
        return new ErrorMsg(Error.SUCCESS, "success");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "登陆")
    public ErrorMsg complete (@ApiParam(value = "用户名")
                              @RequestParam(required = true) String userName,
                              @ApiParam(value = "密码")
                              @RequestParam(required = true) String password

    ) {
        CAccountInfoExample example = new CAccountInfoExample();
        example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5+password,"UTF-8"));

        List<CAccountInfo> infos = userService.selectByExample(example);
        if(infos.size()==0){
            return new ErrorMsg(Error.LOGIN_PASSWORD_ERROR, "用户名密码错误");
        }else{
            return new ErrorMsg(Error.SUCCESS, "登陆成功",infos.get(0));
        }

    }
}
