/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.service.IUserService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.exception.ConnectionFailedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Barrie
 */
@RestController
@RequestMapping(value = "/account")
@Api(description = "车主管理接口")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id获取车主基本信息")
    public ErrorMsg getCAccountInfo(@ApiParam(value = "车主的id")
                                    @RequestParam(required = true)Long id){
        return new ErrorMsg(Error.SUCCESS, "success",userService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册")
    public ErrorMsg register (@ApiParam(value = "车主的基本信息")
                                      @RequestBody(required = true)CAccountInfo accountInfo
                              ) {
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

}
