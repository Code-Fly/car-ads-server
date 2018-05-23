/**
 *
 */
package com.cloud.carads.authorization.service;

import com.cloud.carads.commons.entity.ErrorMsg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Barrie
 */

@FeignClient(value = "car-ads-account")
public interface IAccountService {
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ErrorMsg getUserByUserName(
            @RequestParam("userName") String userName
    );

}
