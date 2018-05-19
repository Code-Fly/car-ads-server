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
import com.cloud.carads.commons.utils.MD5Util;
import com.cloud.carads.constant.SystemConstant;
import com.cloud.carads.sms.entity.SmsLog;
import com.cloud.carads.sms.service.SMSService;
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

    @GetMapping(value = "/querybyid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id获取车主信息")
    public ErrorMsg getCAccountInfo(@ApiParam(value = "车主的id",required = true)
                                    @RequestParam(required = true)Long id){
        CAccountInfo cAccountInfo = userService.selectByPrimaryKey(id);
        if (null == cAccountInfo){
            return new ErrorMsg(Error.C_NOT_EXITS, "车主不存在");
        }else {
            return new ErrorMsg(Error.SUCCESS, "SUCCESS",cAccountInfo);
        }

    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register (@ApiParam(value = "车主的基本信息" ,required = true)
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
        // 查询二级或者三级推荐人
        if(null != accountInfo.getFatherId() && accountInfo.getFatherId()>0){
           CAccountInfo info = userService.selectByPrimaryKey(accountInfo.getFatherId());
           if (null!=info){
               accountInfo.setGrandId(info.getFatherId());
               accountInfo.setGgrandId(info.getGgrandId());
           }

        }
        userService.addCAccount(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success",accountInfo.getId());
    }

    @PostMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主完善信息")
    public ErrorMsg complete (@ApiParam(value = "车主的全部信息",required = true)
                              @RequestBody(required = true)CAccountInfo info
    ) {
        info.setUpdateTime(new Date());
        // 待审核
        info.setFlag(0);
        userService.updateCAccountByID(info);
        return new ErrorMsg(Error.SUCCESS, "success");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "登陆")
    public ErrorMsg complete (@ApiParam(value = "用户名",required = true)
                              @RequestParam(required = true) String userName,
                              @ApiParam(value = "密码",required = true)
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

    @GetMapping(value = "/querybyname", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据用户名或者手机号获取已经审核通过（flag=1）的车主信息")
    public ErrorMsg getCAccountInfoByname(@ApiParam(value = "车主的用户名/手机号", required = true)
                                          @RequestParam(required = true) String userName) {
        CAccountInfoExample example = new CAccountInfoExample();
        CAccountInfoExample.Criteria criteria = example.createCriteria().andUserNameEqualTo(userName).andFlagEqualTo(1);
        example.or(criteria.andMobileNoEqualTo(userName).andFlagEqualTo(1));
        List<CAccountInfo> cAccountInfos = userService.selectByExample(example);
        if (null == cAccountInfos){
            return new ErrorMsg(Error.C_NOT_EXITS, "车主不存在,或未审核通过");
        }else {
            return new ErrorMsg(Error.SUCCESS, "SUCCESS",cAccountInfos.get(0));
        }
    }
}
