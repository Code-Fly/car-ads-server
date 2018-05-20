package com.cloud.carads.commons.controller;

import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.exception.ConnectionFailedException;
import com.cloud.carads.commons.service.ConstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ConstService constService;


    @ExceptionHandler(ConnectionFailedException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorMsg handleConnectionFailedException(ConnectionFailedException ex) {
        logger.error(Error.CONNECTION_FAILED_EXCEPTION.getReasonPhrase(), ex);
        return new ErrorMsg(Error.CONNECTION_FAILED_EXCEPTION.getValue(), Error.CONNECTION_FAILED_EXCEPTION.getReasonPhrase());
    }
}
