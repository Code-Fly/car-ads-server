package com.cloud.carads.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Value("${wechat.setting.appid}")
    public String WECHAT_SETTING_APP_ID;

    @Value("${wechat.setting.appsecret}")
    public String WECHAT_SETTING_APP_SECRET;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "forward:index";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        return "forward:index";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("appId", WECHAT_SETTING_APP_ID);
        model.addAttribute("appSecret", WECHAT_SETTING_APP_SECRET);

        return "index";
    }
}
