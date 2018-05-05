/**
 *
 */
package com.cloud.carads.commons.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author Barrie
 */

@Service
public class ConstService {

    public final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    public final String PATH_SEPARATOR = File.separator;


    /**
     * Setting
     */
    @Value("${wechat.setting.url}")
    public String WECHAT_SETTING_URL;

    @Value("${wechat.setting.appid}")
    public String WECHAT_SETTING_APP_ID;

    @Value("${wechat.setting.appsecret}")
    public String WECHAT_SETTING_APP_SECRET;

    @Value("${wechat.setting.token}")
    public String WECHAT_SETTING_TOKEN;

    @Value("${wechat.setting.cachepath}")
    public String WECHAT_SETTING_CACHE_PATH;

    /**
     * Token
     */
    @Value("${wechat.token.accessToken}")
    public String WECHAT_TOKEN_ACCESS_TOKEN;

    @Value("${wechat.token.expireTime}")
    public String WECHAT_TOKEN_EXPIRE_TIME;

    @Value("${wechat.token.timestamp}")
    public String WECHAT_TOKEN_TIMESTAMP;
    /**
     * Core API
     */
    @Value("${wechat.api.get.callbackip}")
    public String WECHAT_API_GET_CALLBACK_IP;

    @Value("${wechat.api.jsapi.ticket}")
    public String WECHAT_API_JSAPI_TICKET;

    @Value("${wechat.api.access.token}")
    public String WECHAT_API_GET_ACCESS_TOKEN;

    @Value("${wechat.api.menu.create.default}")
    public String URL_MENU_CREATE_DEFAULT;

    @Value("${wechat.api.menu.create.condition}")
    public String URL_MENU_CREATE_CONDITION;

    @Value("${wechat.api.menu.get}")
    public String URL_MENU_GET;

    @Value("${wechat.api.menu.delete.default}")
    public String URL_MENU_DELETE_DEFAULT;

    @Value("${wechat.api.menu.delete.condition}")
    public String URL_MENU_DELETE_CONDITION;

    @Value("${wechat.api.menu.test}")
    public String URL_MENU_TEST;

    /**
     * Account API
     */
    @Value("${wechat.api.sns.oauth2.redirect}")
    public String WECHAT_API_SNS_OAUTH2_REDIRECT;

    @Value("${wechat.api.sns.oauth2.token.get}")
    public String WECHAT_API_SNS_OAUTH2_TOKEN_GET;

    @Value("${wechat.api.sns.oauth2.token.refresh}")
    public String WECHAT_API_SNS_OAUTH2_TOKEN_REFRESH;

    @Value("${wechat.api.user.get.sns.info}")
    public String WECHAT_API_USER_GET_SNS_INFO;

    @Value("${wechat.api.user.get.info}")
    public String WECHAT_API_USER_GET_INFO;

    @Value("${wechat.api.user.get.infos}")
    public String WECHAT_API_USER_GET_INFOS;

    @Value("${wechat.api.user.get.list}")
    public String WECHAT_API_USER_GET_LIST;

    @Value("${wechat.api.user.remark.update}")
    public String WECHAT_API_USER_REMARK_UPDATE;

    @Value("${wechat.api.user.group.get.list}")
    public String WECHAT_API_USER_GROUP_GET_LIST;

    @Value("${wechat.api.user.group.get.by.openid}")
    public String WECHAT_API_USER_GROUP_GET_BY_OPENID;

    @Value("${wechat.api.user.group.rename}")
    public String WECHAT_API_USER_GROUP_RENAME;

    @Value("${wechat.api.user.group.update}")
    public String WECHAT_API_USER_GROUP_UPDATE;

    @Value("${wechat.api.user.group.batchupdate}")
    public String WECHAT_API_USER_GROUP_BATCH_UPDATE;

    @Value("${wechat.api.user.group.delete}")
    public String WECHAT_API_USER_GROUP_DELETE;

    /**
     * Material API
     */
    @Value("${wechat.api.material.get.list}")
    public String WECHAT_API_MATERIAL_GET_LIST;

    @Value("${wechat.api.material.get.detail}")
    public String WECHAT_API_MATERIAL_GET_DETAIL;

    @Value("${wechat.api.media.get}")
    public String WECHAT_API_MEDIA_GET;


    /**
     * Customer Service API
     */
    @Value("${wechat.api.customer.service.message.send}")
    public String WECHAT_API_CUSTOMER_SERVICE_MESSAGE_SEND;

    @Value("${wechat.api.customer.service.message.template.get.list}")
    public String WECHAT_API_CUSTOMER_SERVICE_MESSAGE_TEMPLATE_GET_LIST;

    @Value("${wechat.api.customer.service.message.template.get.id}")
    public String WECHAT_API_CUSTOMER_SERVICE_MESSAGE_TEMPLATE_GET_ID;

    @Value("${wechat.api.customer.service.message.template.send}")
    public String WECHAT_API_CUSTOMER_SERVICE_MESSAGE_TEMPLATE_SEND;

    @Value("${wechat.api.customer.service.kf.list}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_LIST;

    @Value("${wechat.api.customer.service.kf.listOnline}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_LIST_ONLINE;

    @Value("${wechat.api.customer.service.kf.add}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_ADD;

    @Value("${wechat.api.customer.service.kf.update}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_UPDATE;

    @Value("${wechat.api.customer.service.kf.delete}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_DELETE;

    @Value("${wechat.api.customer.service.kf.uploadHeadImg}")
    public String WECHAT_API_CUSTOMER_SERVICE_KF_UPLOAD_HEAD_IMG;

    public String getServerPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource(PATH_SEPARATOR).getPath();

        if (path.startsWith("file:/")) {
            path = "/" + path.substring(6, path.indexOf("/" + "WEB-INF")) + "/";
        } else {
            path = "/" + path.substring(1, path.indexOf("/" + "WEB-INF")) + "/";
        }

        return path;
    }

    /**
     * 获取请求的URL
     *
     * @param request
     * @return
     */
    public String getServerUrl(HttpServletRequest request) {
        String path = request.getContextPath() + "/";
//        int port = request.getServerPort();
//        String basePath = null;
//        if (80 == port) {
//            basePath = request.getScheme() + "://" + request.getServerName() + path;
//        } else {
//            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//        }

        return path;
    }

    /**
     * 获取请求的IP
     *
     * @param request
     * @return
     */
    public String getClientIp(HttpServletRequest request) {
        String remoteAddr = null;

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
