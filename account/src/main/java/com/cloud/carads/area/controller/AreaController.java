package com.cloud.carads.area.controller;

import com.cloud.carads.area.entity.TAreainfo;
import com.cloud.carads.area.entity.TAreainfoExample;
import com.cloud.carads.area.service.IAreaService;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/area")
@Api(description = "省市区管理接口")
public class AreaController {
    @Autowired
    private IAreaService areaService;

    /**
     *
     * 查询区域信息
     * @param areaLevel
     * @param parentid
     * @return
     */
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "获取区域信息")
    public ErrorMsg getAreas(
            @ApiParam(value = "层级 1省 2市 3区/县")
            @RequestParam(required = true) Byte areaLevel,
            @ApiParam(value = "父节点的id")
            @RequestParam(required = false) Integer parentid){
        TAreainfoExample example = new TAreainfoExample();
        TAreainfoExample.Criteria criteria = example.createCriteria();
        criteria.andArealevelEqualTo(areaLevel);
        if(null!=parentid){
            criteria.andParentIdEqualTo(parentid);
        }
        List<TAreainfo> areainfos  = areaService.selectByExample(example);
        return new ErrorMsg(Error.SUCCESS, "success", areainfos);
    }

}
