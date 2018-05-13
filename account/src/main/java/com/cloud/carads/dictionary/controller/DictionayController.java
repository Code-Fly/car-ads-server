package com.cloud.carads.dictionary.controller;

import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ErrorMsg getCAccountInfo(@ApiParam(value = "数据字典code，请看t_dictionary.dic_code")
                                    @RequestParam(required = true)String dicCode){
        return new ErrorMsg(Error.SUCCESS, "success",dictionaryService.queryDictionaryAttrByCode(dicCode));
    }

}
