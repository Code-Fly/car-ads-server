/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoDto;
import com.cloud.carads.account.entity.CAccountInfoExample;
import com.cloud.carads.account.entity.SmsLog;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.account.service.ISMSService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Barrie
 */
@RestController
@Api(description = "车主管理接口", tags = "管理")
public class UserController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISMSService smsService;

    @GetMapping(value = "/user/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id获取车主信息")
    public ErrorMsg getCAccountInfo(@ApiParam(value = "车主的id")
                                    @PathVariable Long id
    ) {
        return new ErrorMsg(Error.SUCCESS, "success", accountService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/user/name/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据用户名或者手机号获取车主信息")
    public ErrorMsg getCAccountInfoByname(@ApiParam(value = "车主的用户名/手机号")
                                          @PathVariable String userName) {
        CAccountInfoExample example = new CAccountInfoExample();
        CAccountInfoExample.Criteria criteria = example.createCriteria().andUserNameEqualTo(userName).andFlagEqualTo(1);
        example.or(criteria.andMobileNoEqualTo(userName).andFlagEqualTo(1));
        return new ErrorMsg(Error.SUCCESS, "success", accountService.selectByExample(example).get(0));
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register(@ApiParam(value = "车主的基本信息")
                             @RequestBody(required = true) CAccountInfoDto accountInfo
    ) {
        // 校验验证码
        System.err.println(new Gson().toJson(smsService.queryLastSMSByPhone(accountInfo.getMobileNo())));
        SmsLog smsLog = (SmsLog) smsService.queryLastSMSByPhone(accountInfo.getMobileNo()).getData();
        //ToDO
       /* if (!accountInfo.getShortCode().equals(smsLog.getContent())){
            return new ErrorMsg(Error.SMS_SHORTCODE_ERROR, "验证码错误。");
        }*/
        // 草稿未完善信息
        accountInfo.setFlag(9);
        // 查询二级或者三级推荐人
        if (null != accountInfo.getFatherId() && accountInfo.getFatherId() > 0) {
            CAccountInfo info = accountService.selectByPrimaryKey(accountInfo.getFatherId());
            if (null != info) {
                accountInfo.setGrandId(info.getFatherId());
                accountInfo.setGgrandId(info.getGgrandId());
            }

        }
        accountService.addCAccount(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success", accountInfo.getId());
    }

    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主完善信息")
    public ErrorMsg complete(@ApiParam(value = "车主的全部信息")
                             @RequestBody(required = true) CAccountInfo info
    ) {
        info.setUpdateTime(new Date());
        // 待审核
        info.setFlag(0);
        accountService.updateCAccountByID(info);
        return new ErrorMsg(Error.SUCCESS, "success");
    }

}
