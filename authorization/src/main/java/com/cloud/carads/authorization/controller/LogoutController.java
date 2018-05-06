package com.cloud.carads.authorization.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@FrameworkEndpoint
@RequestMapping(value = "/oauth")
public class LogoutController {
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

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
