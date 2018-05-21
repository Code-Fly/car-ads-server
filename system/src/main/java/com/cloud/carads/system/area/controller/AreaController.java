package com.cloud.carads.system.area.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.area.entity.TAreainfo;
import com.cloud.carads.system.area.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Api(description = "省市区管理接口", tags = "省市区")
public class AreaController extends BaseController {
    @Autowired
    private IAreaService areaService;

    @GetMapping(value = "/areas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取区域信息")
    public ErrorMsg getList(
            @ApiParam(value = "id")
            @RequestParam(required = false) Integer id,
            @ApiParam(value = "父节点的id")
            @RequestParam(required = false) Integer parentid,
            @ApiParam(value = "名称")
            @RequestParam(required = false) String name,
            @ApiParam(value = "层级 1省 2市 3区/县")
            @RequestParam(required = false) Byte areaLevel,
            @ApiParam(value = "分页参数，页码，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "分页参数，每页条数，page 和 rows 任意一个不为正整数则不分页")
            @RequestParam(value = "rows", required = false, defaultValue = "0") Integer rows

    ) {
        TAreainfo template = new TAreainfo();
        template.setId(id);
        template.setParentId(parentid);
        template.setName(name);
        template.setArealevel(areaLevel);

        List<TAreainfo> list = areaService.getList(template, page, rows);

        DataGrid dg = new DataGrid();
        long count = areaService.getListCount(template);
        dg.setTotal(count);
        dg.setRows(list);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), dg);
    }

    @GetMapping(value = "/area/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取区域信息列表")
    public ErrorMsg getList(
            @ApiParam(value = "区域id")
            @PathVariable(required = false) Integer id
    ) {
        TAreainfo template = new TAreainfo();
        template.setId(id);

        List<TAreainfo> list = areaService.getList(template, 0, 0);

        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), list);
    }

    @PostMapping(value = "/area", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加区域信息列表")
    public ErrorMsg add(
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody TAreainfo areainfo
    ) {
        areaService.add(areainfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), areaService.getList(areainfo, 0, 0).get(0));
    }

    @PutMapping(value = "/area/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新区域信息")
    public ErrorMsg update(
            @ApiParam(value = "区域id")
            @PathVariable Integer id,
            @ApiParam(value = "用户信息,不需要使用的字段不添加或者写null")
            @RequestBody TAreainfo areainfo
    ) {
        areainfo.setId(id);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), areaService.update(areainfo));
    }

    @DeleteMapping(value = "/area/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除区域信息")
    public ErrorMsg update(
            @ApiParam(value = "区域id")
            @PathVariable Integer id
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), areaService.delete(Arrays.asList(id)));
    }

    @DeleteMapping(value = "/areas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除区域信息")
    public ErrorMsg update(
            @ApiParam(value = "区域ids")
            @RequestBody List<Integer> ids
    ) {
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), areaService.delete(ids));
    }

}
