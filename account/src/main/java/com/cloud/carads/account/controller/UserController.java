/**
 *
 */
package com.cloud.carads.account.controller;

import com.cloud.carads.account.service.IUserService;
import com.cloud.wechat.commons.controller.BaseController;
import com.cloud.wechat.commons.entity.Error;
import com.cloud.wechat.commons.entity.ErrorMsg;
import com.cloud.wechat.commons.exception.ConnectionFailedException;
import com.cloud.wechat.commons.exception.WeChatException;
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
     * @throws WeChatException
     */
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户列表")
    public ErrorMsg getWeChatUserList(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @ApiParam(value = "起始openId，0为第一个")
                                      @RequestParam(required = false, defaultValue = "0") String nextOpenId
    ) throws ConnectionFailedException, WeChatException, UnsupportedEncodingException {

        if ("0".equals(nextOpenId)) {
            nextOpenId = null;
        }

//        JsonObject result = userService.getWeChatUserList(nextOpenId);
        return new ErrorMsg(Error.SUCCESS, "success", 111);
    }


}
