/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
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

        DataGrid dg = new DataGrid();
        long count = accountService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取车主信息列表")
    public ErrorMsg getList(@ApiParam(value = "车主id")
                            @PathVariable(required = false) Long id

    ) {
        CAccountInfo template = new CAccountInfo();
        template.setId(id);

        List<CAccountInfo> list = accountService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加车主信息列表")
    public ErrorMsg add(@ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
                        @RequestBody CAccountInfo accountInfo


    ) {
        List<CAccountInfo> users;
        CAccountInfo tUserName = new CAccountInfo();
        tUserName.setUserName(accountInfo.getUserName());
        users = accountService.getList(tUserName, 0, 0);
        if (users.size() >= 1) {
            logger.error(Error.USER_ALREADY_EXIST_ERROR.getReasonPhrase());
            return new ErrorMsg(Error.USER_ALREADY_EXIST_ERROR.getValue(), Error.USER_ALREADY_EXIST_ERROR.getReasonPhrase());
        }
        if (null != accountInfo.getPassword()) {
            accountInfo.setPassword(new StandardPasswordEncoder().encode(accountInfo.getPassword()));
        }
        accountInfo.setCreateTime(new Date());
        accountService.add(accountInfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), accountService.getList(accountInfo, 0, 0).get(0));
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
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), accountService.update(accountInfo));
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除车主信息")
    public ErrorMsg update(@ApiParam(value = "车主id")
                           @PathVariable Long id
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), accountService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除车主信息")
    public ErrorMsg update(@ApiParam(value = "车主ids")
                           @RequestBody List<Long> ids
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), accountService.delete(ids));
    }
}
