package com.cloud.carads.system.area.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.system.area.entity.TAreainfo;
import com.cloud.carads.system.area.entity.TAreainfoExample;
import com.cloud.carads.system.area.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "省市区管理接口", tags = "省市区")
public class AreaController extends BaseController {
    @Autowired
    private IAreaService areaService;

    /**
     * 查询区域信息
     *
     * @param areaLevel
     * @param parentid
     * @return
     */
    @GetMapping(value = "/area", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取区域信息")
    public ErrorMsg getAreas(
            @ApiParam(value = "层级 1省 2市 3区/县", required = true)
            @RequestParam(required = true) Byte areaLevel,
            @ApiParam(value = "父节点的id")
            @RequestParam(required = false) Integer parentid) {
        TAreainfoExample example = new TAreainfoExample();
        TAreainfoExample.Criteria criteria = example.createCriteria();
        criteria.andArealevelEqualTo(areaLevel);
        if (null != parentid) {
            criteria.andParentIdEqualTo(parentid);
        }
        List<TAreainfo> areainfos = areaService.selectByExample(example);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), areainfos);
    }

}
