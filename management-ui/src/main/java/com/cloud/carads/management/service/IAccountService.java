/**
 *
 */
package com.cloud.carads.management.service;

import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.management.entity.CAccountInfoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Barrie
 */

@FeignClient(value = "car-ads-account")
public interface IAccountService {
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    ErrorMsg register(@ApiParam(value = "车主的基本信息")
                      @RequestBody(required = true) CAccountInfoDto accountInfo);

}
