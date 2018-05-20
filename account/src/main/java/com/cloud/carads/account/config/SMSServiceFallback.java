package com.cloud.carads.account.config;

import com.cloud.carads.account.service.ISMSService;
import com.cloud.carads.commons.entity.ErrorMsg;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SMSServiceFallback implements ISMSService {
    @Override
    public ErrorMsg queryLastSMSByPhone(String phoneNo) {
        return new ErrorMsg(HttpStatus.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
    }
}
