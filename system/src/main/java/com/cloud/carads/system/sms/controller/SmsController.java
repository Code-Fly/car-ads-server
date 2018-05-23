package com.cloud.carads.system.sms.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.sms.entity.SmsLog;
import com.cloud.carads.system.sms.service.ISMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
    @GetMapping(value = "/smss/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送验证码")
    public ErrorMsg getCAccountInfo(
            HttpServletRequest request,
            @ApiParam(value = "发送手机号码")
            @RequestParam(required = true) String phoneNo
    ) {
        int shortCode = (int) ((Math.random() * 9 + 1) * 100000);
        SmsLog template = new SmsLog();
        template.setPhoneNo(phoneNo);
        template.setChannel("aliyun");
        template.setFromIp(request.getRemoteAddr());
        template.setContent(String.valueOf(shortCode));
        smsService.add(template);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), shortCode);
    }


    @GetMapping(value = "/smss/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取最新短信的车主app信息,返回的字段说明请看c_app_version表")
    public ErrorMsg getAPPVersion(
            @ApiParam(value = "发送手机号码")
            @RequestParam(required = true) String phoneNo
    ) {
        SmsLog template = new SmsLog();
        template.setPhoneNo(phoneNo);
        List<SmsLog> versions = smsService.getLatest(template);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), versions.get(0));
    }

    @GetMapping(value = "/smss", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取短信信息")
    public ErrorMsg getList(
            @ApiParam(value = "id")
            @RequestParam(required = false) Integer id,
            @ApiParam(value = "发送手机号码")
            @RequestParam(required = false) String phoneNo,
            @ApiParam(value = "来源IP")
            @RequestParam(required = false) String fromIp,
            @ApiParam(value = "短信通道")
            @RequestParam(required = false) String channel,
            @ApiParam(value = "发送状态")
            @RequestParam(required = false) String status,
            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        SmsLog template = new SmsLog();
        template.setId(id);
        template.setPhoneNo(phoneNo);
        template.setFromIp(fromIp);
        template.setChannel(channel);
        template.setStatus(status);

        List<SmsLog> list = smsService.getList(template, page, rows);

        DataGrid dg = new DataGrid();
        long count = smsService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/sms/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取短信信息列表")
    public ErrorMsg getList(
            @ApiParam(value = "短信id")
            @PathVariable(required = false) Integer id
    ) {
        SmsLog template = new SmsLog();
        template.setId(id);

        List<SmsLog> list = smsService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加短信信息列表")
    public ErrorMsg add(
            @ApiParam(value = "短信信息,不需要使用的字段不添加或者写null")
            @RequestBody SmsLog areainfo
    ) {
        smsService.add(areainfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), smsService.getList(areainfo, 0, 0).get(0));
    }

    @PutMapping(value = "/sms/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新短信信息")
    public ErrorMsg update(
            @ApiParam(value = "短信id")
            @PathVariable Integer id,
            @ApiParam(value = "短信信息,不需要使用的字段不添加或者写null")
            @RequestBody SmsLog smsLog

    ) {
        smsLog.setId(id);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), smsService.update(smsLog));
    }

    @DeleteMapping(value = "/sms/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除短信信息")
    public ErrorMsg update(
            @ApiParam(value = "短信id")
            @PathVariable Integer id
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), smsService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/smss", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除短信信息")
    public ErrorMsg update(
            @ApiParam(value = "短信ids")
            @RequestBody List<Integer> ids
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), smsService.delete(ids));
    }
}
