package com.cloud.carads.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Barrie
 */
public class BaseService {
    // logger
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ConstService constService;

//    @Autowired
//    protected TokenServiceImpl tokenService;

//    @Autowired
//    protected HttpService httpService;

//    @Autowired
//    protected FileService fileService;

}
