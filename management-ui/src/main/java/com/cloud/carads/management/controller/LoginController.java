package com.cloud.carads.management.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.utils.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    @Value("${wechat.setting.appid}")
    public String WECHAT_SETTING_APP_ID;

    @Value("${wechat.setting.appsecret}")
    public String WECHAT_SETTING_APP_SECRET;

    @Value("${security.oauth2.client.user-logout-uri}")
    public String USER_LOGOUT_URI;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "forward:index";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        return "forward:index";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model,
                        Principal principal) {
        SecurityContext context = SecurityContextHolder.getContext();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) context.getAuthentication().getDetails();

        String localLogoutUri = ConfigUtil.getServerUrl(request, true) + "/logout";

        model.addAttribute("name", principal.getName());
        model.addAttribute("token", details.getTokenValue());
        model.addAttribute("logoutUri", USER_LOGOUT_URI);
        model.addAttribute("localLogoutUri", localLogoutUri);
        model.addAttribute("appId", WECHAT_SETTING_APP_ID);
        model.addAttribute("appSecret", WECHAT_SETTING_APP_SECRET);

        return "index";
    }
}
