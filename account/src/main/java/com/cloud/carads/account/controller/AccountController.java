/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoDto;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.account.service.ISMSService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.utils.MD5Util;
import com.cloud.carads.constant.SystemConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Barrie
 */
@RestController
@Api(description = "用户管理接口", tags = "用户")
public class AccountController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISMSService smsService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取车主信息列表")
    public ErrorMsg getList(@ApiParam(value = "车主id")
                            @RequestParam(required = false) Long id,
                            @ApiParam(value = "用户名")
                            @RequestParam(required = false) String userName,
                            @ApiParam(value = "推荐人id")
                            @RequestParam(required = false) Long fatherId,
                            @ApiParam(value = "grandId")
                            @RequestParam(required = false) Long grandId,
                            @ApiParam(value = "ggrandId")
                            @RequestParam(required = false) Long ggrandId,
                            @ApiParam(value = "手机号")
                            @RequestParam(required = false) String mobileNo,
                            @ApiParam(value = "身份证号")
                            @RequestParam(required = false) String identityNo,
                            @ApiParam(value = "车牌号")
                            @RequestParam(required = false) String plateNo,
                            @ApiParam(value = "审核状态，0待审核 1审核通过2审核未通过 5已删除 9草稿未完善信息")
                            @RequestParam(required = false) Integer flag,
                            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
                            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
                            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        CAccountInfo template = new CAccountInfo();
        template.setId(id);
        template.setUserName(userName);
        template.setFatherId(fatherId);
        template.setGrandId(grandId);
        template.setGgrandId(ggrandId);
        template.setMobileNo(mobileNo);
        template.setIdentityNo(identityNo);
        template.setPlateNo(plateNo);
        template.setFlag(flag);

        List<CAccountInfo> list = accountService.getList(template, page, rows);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取车主信息列表")
    public ErrorMsg getList(@ApiParam(value = "车主id")
                            @PathVariable(required = false) Long id

    ) {
        CAccountInfo template = new CAccountInfo();
        template.setId(id);

        List<CAccountInfo> list = accountService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @PostMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加车主信息列表")
    public ErrorMsg add(@ApiParam(value = "车主id")
                        @PathVariable Long id,
                        @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
                        @RequestBody CAccountInfo accountInfo


    ) {
        accountInfo.setId(id);
        if (null != accountInfo.getPassword()) {
            accountInfo.setPassword(new StandardPasswordEncoder().encode(accountInfo.getPassword()));
        }
        accountInfo.setCreateTime(new Date());
        accountService.add(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success", accountInfo);
    }

    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新车主信息")
    public ErrorMsg update(@ApiParam(value = "车主id")
                           @PathVariable Long id,
                           @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
                           @RequestBody CAccountInfo accountInfo


    ) {
        accountInfo.setId(id);
        if (null != accountInfo.getPassword()) {
            accountInfo.setPassword(new StandardPasswordEncoder().encode(accountInfo.getPassword()));
        }
        accountInfo.setUpdateTime(new Date());
        accountService.update(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success", accountInfo);
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除车主信息")
    public ErrorMsg update(@ApiParam(value = "车主id")
                           @PathVariable Long id
    ) {
        return new ErrorMsg(Error.SUCCESS, "success", accountService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除车主信息")
    public ErrorMsg update(@ApiParam(value = "车主ids")
                           @RequestBody List<Long> ids
    ) {
        return new ErrorMsg(Error.SUCCESS, "success", accountService.delete(ids));
    }


    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register(@ApiParam(value = "车主的基本信息")
                             @RequestBody(required = true) CAccountInfoDto accountInfo
    ) {
        // 校验验证码
        ErrorMsg smsLog = smsService.queryLastSMSByPhone(accountInfo.getMobileNo());
        //ToDO
       /* if (!accountInfo.getShortCode().equals(smsLog.getContent())){
            return new ErrorMsg(Error.SMS_SHORTCODE_ERROR, "验证码错误。");
        }*/
        // 草稿未完善信息
        accountInfo.setFlag(9);
        // 查询二级或者三级推荐人
        if (null != accountInfo.getFatherId() && accountInfo.getFatherId() > 0) {
            CAccountInfo gtemplate = new CAccountInfo();
            gtemplate.setId(accountInfo.getFatherId());

            List<CAccountInfo> infos = accountService.getList(gtemplate, 0, 0);
            if (infos.size() > 0) {
                accountInfo.setGrandId(infos.get(0).getFatherId());
                accountInfo.setGgrandId(infos.get(0).getGgrandId());
            }
        }
        accountInfo.setPassword(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5 + accountInfo.getPassword(), "UTF-8"));
        accountService.add(accountInfo);
        return new ErrorMsg(Error.SUCCESS, "success", accountInfo.getId());
    }
}
