package com.cloud.carads.system.dictionary.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.dictionary.entity.TdictionaryAttr;
import com.cloud.carads.system.dictionary.service.IDictionaryAttrService;
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
@Api(description = "数据字典接口", tags = "数据字典")
public class DictionaryAttrController extends BaseController {

    @Autowired
    private IDictionaryAttrService dictionaryService;

    @GetMapping(value = "/dictionaryAttrs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取字典所有属性信息")
    public ErrorMsg getList(
            @ApiParam(value = "id")
            @RequestParam(required = false) Long id,
            @ApiParam(value = "字典code")
            @RequestParam(required = false) String dicCode,
            @ApiParam(value = "属性code")
            @RequestParam(required = false) String attrCode,
            @ApiParam(value = "属性值,模糊匹配")
            @RequestParam(required = false) String attrValue,
            @ApiParam(value = "1有效 0无效")
            @RequestParam(required = false) Integer flag,
            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        TdictionaryAttr template = new TdictionaryAttr();
        template.setId(id);
        template.setDicCode(dicCode);
        template.setAttrCode(attrCode);
        template.setAttrValue(attrValue);
        template.setFlag(flag);
        template.setUpdateTime(new Date());

        List<TdictionaryAttr> list = dictionaryService.getList(template, page, rows);

        DataGrid dg = new DataGrid();
        long count = dictionaryService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/dictionaryAttr/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取字典所有属性信息列表")
    public ErrorMsg getList(
            @ApiParam(value = "字典所有属性id")
            @PathVariable(required = false) Long id
    ) {
        TdictionaryAttr template = new TdictionaryAttr();
        template.setId(id);

        List<TdictionaryAttr> list = dictionaryService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/dictionaryAttr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加字典所有属性信息列表")
    public ErrorMsg add(
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody TdictionaryAttr areainfo
    ) {
        dictionaryService.add(areainfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.getList(areainfo, 0, 0).get(0));
    }

    @PutMapping(value = "/dictionaryAttr/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新字典所有属性信息")
    public ErrorMsg update(
            @ApiParam(value = "字典所有属性id")
            @PathVariable Long id,
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody TdictionaryAttr tdictionaryAttr


    ) {
        tdictionaryAttr.setUpdateTime(new Date());
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.update(tdictionaryAttr));
    }

    @DeleteMapping(value = "/dictionaryAttr/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除字典所有属性信息")
    public ErrorMsg update(
            @ApiParam(value = "字典所有属性id")
            @PathVariable Long id
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/dictionaryAttrs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除字典所有属性信息")
    public ErrorMsg update(
            @ApiParam(value = "字典所有属性ids")
            @RequestBody List<Long> ids
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dictionaryService.delete(ids));
    }
}
