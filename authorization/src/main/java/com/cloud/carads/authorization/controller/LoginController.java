package com.cloud.carads.authorization.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/oauth")
public class LoginController {
    @Value("${wechat.setting.appid}")
    public String WECHAT_SETTING_APP_ID;

    @Value("${wechat.setting.appsecret}")
    public String WECHAT_SETTING_APP_SECRET;

    @GetMapping(value = "/login")
    public String index(Model model) {
        model.addAttribute("name", "Dear");
        model.addAttribute("appId", WECHAT_SETTING_APP_ID);
        model.addAttribute("appSecret", WECHAT_SETTING_APP_SECRET);

        return "login";
    }


}
