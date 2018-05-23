package com.cloud.carads.account.service;

import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "car-ads-system")
public interface ISMSService {
    @GetMapping(value = "/smss/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取最新短信的车主app信息,返回的字段说明请看c_app_version表")
    ErrorMsg getAPPVersion(
            @ApiParam(value = "发送手机号码")
            @RequestParam(value = "phoneNo", required = true) String phoneNo
    );
}
