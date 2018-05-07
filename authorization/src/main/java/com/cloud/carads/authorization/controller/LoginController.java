package com.cloud.carads.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/oauth")
public class LoginController {

    @GetMapping(value = "/login")
    public String index(Model model) {
        return "login";
    }

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         RedirectAttributes attr,
                         Model model,
                         @RequestParam String token,
                         @RequestParam String redirect
    ) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (null != accessToken) {
            tokenStore.removeAccessToken(accessToken);
        }


        // token can be revoked here if needed
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            String tokenValue = authHeader.replace("Bearer", "").trim();
//            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//            tokenStore.removeAccessToken(accessToken);
//        }

        new SecurityContextLogoutHandler().logout(request, null, null);
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        model.addAttribute("redirect", redirect);
//        model.addAttribute("refererUri", request.getHeader("referer"));
//        try {
//            //sending back to client app
////            response.sendRedirect(request.getHeader("referer"));
//            response.sendRedirect("http://127.0.0.1:8080/logout");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        attr.addAttribute("token", token);
//        attr.addAttribute("redirect", redirect);
        return "login";
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request,
                     HttpServletResponse response,
                     Model model,
                     @RequestParam String redirect
    ) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        try {
            //sending back to client app
//            response.sendRedirect(request.getHeader("referer"));
//            response.sendRedirect("http://127.0.0.1:8080/logout");
            response.sendRedirect(redirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
