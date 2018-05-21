package com.cloud.carads.system.capp.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.capp.entity.CAppVersion;
import com.cloud.carads.system.capp.service.ICAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/capp")
@Api(description = "车主app接口", tags = "车主app")
public class CAppController extends BaseController {
    @Autowired
    private ICAppService cAppService;

    @GetMapping(value = "/versions/latest")
    @ApiOperation(value = "获取最新版本的车主app信息,返回的字段说明请看c_app_version表")
    public ErrorMsg getAPPVsersion(
            @ApiParam(value = "android,ios")
            @RequestParam(required = true) String os
    ) {
        CAppVersion template = new CAppVersion();
        template.setOs(os);
        List<CAppVersion> versions = cAppService.getLatest(template);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), versions.get(0));
    }

    @GetMapping(value = "/versions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取版本信息")
    public ErrorMsg getList(
            @ApiParam(value = "id")
            @RequestParam(required = false) Integer id,
            @ApiParam(value = "版本号")
            @RequestParam(required = false) String versionCode,
            @ApiParam(value = "版本号")
            @RequestParam(required = false) String versionName,
            @ApiParam(value = "修改时间")
            @RequestParam(required = false) Integer updateTime,
            @ApiParam(value = "app大小")
            @RequestParam(required = false) String appSize,
            @ApiParam(value = "是否强更 1：强更  0：非强更")
            @RequestParam(required = false) Integer strongUpt,
            @ApiParam(value = "android,ios")
            @RequestParam(required = false) String os,
            @ApiParam(value = "app下载地址")
            @RequestParam(required = false) String downloadUrl,
            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        CAppVersion template = new CAppVersion();
        template.setId(id);
        template.setVersionCode(versionCode);
        template.setVersionName(versionName);
        template.setUpdateTime(updateTime);
        template.setAppSize(appSize);
        template.setStrongUpt(strongUpt);
        template.setOs(os);
        template.setDownloadUrl(downloadUrl);

        List<CAppVersion> list = cAppService.getList(template, page, rows);

        DataGrid dg = new DataGrid();
        long count = cAppService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/version/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取版本信息列表")
    public ErrorMsg getList(
            @ApiParam(value = "版本id")
            @PathVariable(required = false) Integer id
    ) {
        CAppVersion template = new CAppVersion();
        template.setId(id);

        List<CAppVersion> list = cAppService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/version", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加版本信息列表")
    public ErrorMsg add(
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody CAppVersion areainfo
    ) {
        cAppService.add(areainfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), cAppService.getList(areainfo, 0, 0).get(0));
    }

    @PutMapping(value = "/version/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新版本信息")
    public ErrorMsg update(
            @ApiParam(value = "版本id")
            @PathVariable Integer id,
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody CAppVersion appVersion

    ) {
        appVersion.setId(id);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), cAppService.update(appVersion));
    }

    @DeleteMapping(value = "/version/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除版本信息")
    public ErrorMsg update(
            @ApiParam(value = "版本id")
            @PathVariable Integer id
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), cAppService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/versions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除版本信息")
    public ErrorMsg update(
            @ApiParam(value = "版本ids")
            @RequestBody List<Integer> ids
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), cAppService.delete(ids));
    }
}
