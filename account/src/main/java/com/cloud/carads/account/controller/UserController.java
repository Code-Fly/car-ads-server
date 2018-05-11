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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Barrie
 */
@RestController
@RequestMapping(value = "/account")
@Api(description = "用户管理接口")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;


    /**
     * 获取用户列表
     *
     * @param request    request
     * @param response   response
     * @param nextOpenId nextOpenId
     * @return
     * @throws ConnectionFailedException
     */
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户列表")
    public ErrorMsg getWeChatUserList(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @ApiParam(value = "起始openId，0为第一个")
                                      @RequestParam(required = false, defaultValue = "0") String nextOpenId
    ) throws ConnectionFailedException, UnsupportedEncodingException {

        if ("0".equals(nextOpenId)) {
            nextOpenId = null;
        }

//        JsonObject result = userService.getWeChatUserList(nextOpenId);
        return new ErrorMsg(Error.SUCCESS, "success", 111);
    }

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "用户注册")
    public ErrorMsg register (@ApiParam(value = "起始openId，0为第一个")
                                      @RequestParam(required = true)CAccountInfo info
                              ) {
        userService.addCAccount(info);
        return new ErrorMsg(Error.SUCCESS, "success", 111);
    }

    @GetMapping(value = "/complete", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "用户完善信息")
    public ErrorMsg complete (@ApiParam(value = "起始openId，0为第一个")
                              @RequestParam(required = true)CAccountInfo info
    ) {
        info.setUpdateTime(new Date());
        // 待审核
        info.setFlag(0);
        userService.updateCAccountByID(info);
        return new ErrorMsg(Error.SUCCESS, "success");
    }

}
