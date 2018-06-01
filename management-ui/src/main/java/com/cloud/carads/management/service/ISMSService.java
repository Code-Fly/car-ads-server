package com.cloud.carads.management.service;

import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "car-ads-system")
public interface ISMSService {
    @GetMapping(value = "/smss/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "发送验证码")
    ErrorMsg sendShortCode(
            @ApiParam(value = "发送手机号码")
            @RequestParam(value = "phoneNo", required = true) String phoneNo
    );
}
