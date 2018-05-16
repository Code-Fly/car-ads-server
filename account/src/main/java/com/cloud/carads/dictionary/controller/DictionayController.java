package com.cloud.carads.dictionary.controller;

import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.dictionary.entity.TdictionaryAttrExample;
import com.cloud.carads.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典接口
 */
@RestController
@RequestMapping(value = "/dictionay")
@Api(description = "数据字典接口")
public class DictionayController {

    @Autowired
    private DictionaryService dictionaryService;
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据字典code查询所有属性和对应的值")
    public ErrorMsg getCAccountInfo(@ApiParam(value = "数据字典code，请看t_dictionary.dic_code", required = true)
                                    @RequestParam(required = true)String dicCode,
                                    @ApiParam(value = "属性值，t_dictionary_attr.attr_value模糊查询")
                                    @RequestParam(required = false)String attrValue){

        TdictionaryAttrExample example = new TdictionaryAttrExample();
        TdictionaryAttrExample.Criteria criteria = example.createCriteria();
        criteria.andDicCodeEqualTo(dicCode);
        if(StringUtils.isNotBlank(attrValue)){
            criteria.andAttrValueLike(attrValue);
        }
        return new ErrorMsg(Error.SUCCESS, "success",dictionaryService.selectByExample(example));
    }



}
