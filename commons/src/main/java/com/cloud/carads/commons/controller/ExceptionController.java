package com.cloud.carads.commons.controller;

import com.cloud.carads.commons.entity.ErrorMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(value = "/400", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg BadRequest() {
        logger.error(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ErrorMsg(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }


    @GetMapping(value = "/401", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMsg missingLogin() {
        logger.warn(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return new ErrorMsg(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    @GetMapping(value = "/403", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMsg forbiddenDirectoryListing() {
        logger.warn(HttpStatus.FORBIDDEN.getReasonPhrase());
        return new ErrorMsg(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    @GetMapping(value = "/404", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMsg missingResource() {
        logger.warn(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ErrorMsg(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @GetMapping(value = "/500", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsg uncaughtException(HttpServletRequest request) {
        // retrieve some useful information from the request
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        // Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");

        logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @GetMapping(value = "/503", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMsg unsupportedServletMethod() {
        logger.warn(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        return new ErrorMsg(HttpStatus.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
    }


}
