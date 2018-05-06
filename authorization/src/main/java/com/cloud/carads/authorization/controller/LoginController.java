package com.cloud.carads.authorization.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Value("${wechat.setting.appid}")
    public String WECHAT_SETTING_APP_ID;

    @Value("${wechat.setting.appsecret}")
    public String WECHAT_SETTING_APP_SECRET;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//
//        return "forward:index";
//    }
//
@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index(Model model) {
    model.addAttribute("name", "Dear");
    model.addAttribute("appId", WECHAT_SETTING_APP_ID);
    model.addAttribute("appSecret", WECHAT_SETTING_APP_SECRET);

    return "index";
}

    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request)
            throws Exception {

        return "oauth/oauth_approval";
    }
}
