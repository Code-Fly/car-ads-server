package com.cloud.carads.system.dictionary.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.dictionary.entity.Tdictionary;
import com.cloud.carads.system.dictionary.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 数据字典接口
 */
@RestController
@Api(description = "数据字典详情接口", tags = "字典详情")
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService dictionaryService;

    @GetMapping(value = "/dictionary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取字典详情信息")
    public ErrorMsg getList(
            @ApiParam(value = "字典code")
            @RequestParam(required = false) String dicCode,
            @ApiParam(value = "1有效 0 无效")
            @RequestParam(required = false) Integer flag,
            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        Tdictionary template = new Tdictionary();
        template.setDicCode(dicCode);
        template.setFlag(flag);
        template.setCreateTime(new Date());

        List<Tdictionary> list = dictionaryService.getList(template, page, rows);

        DataGrid dg = new DataGrid();
        long count = dictionaryService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/dictionary/{dicCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取字典详情信息列表")
    public ErrorMsg getList(
            @ApiParam(value = "字典code")
            @PathVariable(required = false) String dicCode
    ) {
        Tdictionary template = new Tdictionary();
        template.setDicCode(dicCode);

        List<Tdictionary> list = dictionaryService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/dictionary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加字典详情信息列表")
    public ErrorMsg add(
            @ApiParam(value = "字典详情,不需要使用的字段不添加或者写null")
            @RequestBody Tdictionary areainfo
    ) {
        dictionaryService.add(areainfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.getList(areainfo, 0, 0).get(0));
    }

    @PutMapping(value = "/dictionary/{dicCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新字典详情信息")
    public ErrorMsg update(
            @ApiParam(value = "字典code")
            @PathVariable(required = false) String dicCode,
            @ApiParam(value = "字典详情,不需要使用的字段不添加或者写null")
            @RequestBody Tdictionary tdictionary


    ) {
        tdictionary.setDicCode(dicCode);
        tdictionary.setUpdateTime(new Date());
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.update(tdictionary));
    }

    @DeleteMapping(value = "/dictionary/{dicCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除字典详情信息")
    public ErrorMsg update(
            @ApiParam(value = "字典code")
            @PathVariable(required = false) String dicCode
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.delete(Arrays.asList(dicCode)));
    }

    @DeleteMapping(value = "/dictionary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除字典详情信息")
    public ErrorMsg update(
            @ApiParam(value = "字典详情codes")
            @RequestBody List<String> dicCodes
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.delete(dicCodes));
    }
}
