package com.cloud.carads.system.sms.controller;

import com.aliyuncs.exceptions.ClientException;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.sms.entity.SmsLog;
import com.cloud.carads.system.sms.service.ISMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(description = "短信接口", tags = "短信")
public class SmsController extends BaseController {
    @Autowired
    private ISMSService smsService;

    /**
     * 发送手机号
     * 记录发送来源ip
     *
     * @param request
     * @param phoneNo
     * @return
     */
    @GetMapping(value = "/sms/send/shortcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送验证码")
    public ErrorMsg getCAccountInfo(
            HttpServletRequest request,
            @ApiParam(value = "手机号", required = true)
            @RequestParam(required = true) String phoneNo) {
        int shortCode = 0;
        try {
            shortCode = smsService.smsCode(phoneNo, request.getRemoteAddr());
        } catch (ClientException e) {
            logger.error("验证码发送失败", e);
        }
        if (shortCode == 0) {
            return new ErrorMsg(Error.SMS_SEND_ERROR, "发送失败，请重发！");
        } else {
            return new ErrorMsg(Error.SUCCESS, "发送成功", shortCode);
        }

    }

    @GetMapping(value = "/sms/phone/{phoneNo}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据手机号查短信记录")
    public ErrorMsg queryLastSMSByPhone(
            HttpServletRequest request,
            @ApiParam(value = "手机号", required = true)
            @PathVariable String phoneNo) {
        SmsLog smsLog = smsService.queryLastSMSByPhone(phoneNo);
        return new ErrorMsg(Error.SUCCESS, "请求成功", smsLog);
    }
}
