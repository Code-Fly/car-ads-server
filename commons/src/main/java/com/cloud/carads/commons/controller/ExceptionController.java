package com.cloud.carads.commons.controller;

import com.cloud.carads.commons.entity.ErrorMsg;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Barrie on 15/12/21.
 */
@RestController
@RequestMapping(value = "/error")
public class ExceptionController extends BaseController {
    @RequestMapping(value = "/400")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String BadRequest() {
        logger.error("Bad Request");
        ErrorMsg errMsg = new ErrorMsg(Integer.valueOf(HttpStatus.BAD_REQUEST.toString()), "Bad Request");
        return new Gson().toJson(errMsg);
    }


    @RequestMapping(value = "/401")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String missingLogin() {
        logger.warn("Missing login");
        ErrorMsg errMsg = new ErrorMsg(Integer.valueOf(HttpStatus.UNAUTHORIZED.toString()), "Missing login");
        return new Gson().toJson(errMsg);
    }

    @RequestMapping(value = "/403")
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String forbiddenDirectoryListing() {
        logger.warn("Forbidden directory listing");
        ErrorMsg errMsg = new ErrorMsg(Integer.valueOf(HttpStatus.FORBIDDEN.toString()), "Forbidden directory listing");
        return new Gson().toJson(errMsg);
    }

    @RequestMapping(value = "/404")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMsg missingResource() {
        logger.warn("Missing resource");
        return new ErrorMsg(Integer.valueOf(HttpStatus.NOT_FOUND.toString()), "Missing resource");
    }

    @RequestMapping(value = "/500")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsg uncaughtException(HttpServletRequest request) {
        // retrieve some useful information from the request
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        // Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");

        logger.error("Uncaught exception", ex);
        return new ErrorMsg(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()), ex.getMessage());
    }

    @RequestMapping(value = "/503")
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMsg unsupportedServletMethod() {
        logger.warn("Unsupported servlet method");
        return new ErrorMsg(Integer.valueOf(HttpStatus.SERVICE_UNAVAILABLE.toString()), "Unsupported servlet method");
    }


}
