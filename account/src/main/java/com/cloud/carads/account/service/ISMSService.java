package com.cloud.carads.account.service;

import com.cloud.carads.account.config.SMSServiceFallback;
import com.cloud.carads.commons.entity.ErrorMsg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "car-ads-system", fallback = SMSServiceFallback.class)
public interface ISMSService {
    @GetMapping(value = "/sms/phone/{phoneNo}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ErrorMsg queryLastSMSByPhone(@PathVariable("phoneNo") String phoneNo);
}
