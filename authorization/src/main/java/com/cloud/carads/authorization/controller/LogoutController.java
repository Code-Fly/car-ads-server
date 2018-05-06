package com.cloud.carads.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@FrameworkEndpoint
@RequestMapping(value = "/oauth")
public class LogoutController {
    @Autowired
    private TokenStore tokenStore;

//    @Autowired
//    @Qualifier("consumerTokenServices")
//    ConsumerTokenServices consumerTokenServices;
//
//    @GetMapping(value = "/logout")
//    @ResponseBody
//    public String revokeToken(String access_token) {
//        if (consumerTokenServices.revokeToken(access_token)) {
//            return "注销成功";
//        } else {
//            return "注销失败";
//        }
//    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam String access_token
    ) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
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
//        try {
//            //sending back to client app
////            response.sendRedirect(request.getHeader("referer"));
//            response.sendRedirect("http://127.0.0.1:8080/logout");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "logout";
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request,
                     HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        try {
            //sending back to client app
//            response.sendRedirect(request.getHeader("referer"));
            response.sendRedirect("http://127.0.0.1:8080/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
