package com.cloud.carads.authorization.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/oauth")
public class UserController {
    @RequestMapping("/user")
    public User user(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));

        User principal = new User("test037", "Pass1234", true, true, true, true, createAuthorityList(new ArrayList<>()));


//        String param = "{\n" +
//                "\t\"firstname\": \"test037\",\n" +
//                "\t\"lastname\": \"test037\",\n" +
//                "\t\"loginname\": \"test037\",\n" +
//                "\t\"tenant\": \"DEFAULT\",\n" +
//                "\t\"username\": \"test037\",\n" +
//                "\t\"authorities\": [" +
//                "{\n" +
//                "\t\t\"role\": \"CREATE_REPOSITORY\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"CREATE_ROLLOUT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"CREATE_TARGET\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"DELETE_REPOSITORY\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"DELETE_ROLLOUT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"DELETE_TARGET\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"DOWNLOAD_REPOSITORY_ARTIFACT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"HANDLE_ROLLOUT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"READ_REPOSITORY\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"READ_ROLLOUT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"READ_TARGET\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"READ_TARGET_SECURITY_TOKEN\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"SYSTEM_ADMIN\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"TENANT_CONFIGURATION\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"UPDATE_REPOSITORY\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"UPDATE_ROLLOUT\"\n" +
//                "\t}, {\n" +
//                "\t\t\"role\": \"UPDATE_TARGET\"\n" +
//                "\t}" +
//                "],\n" +
//                "\t\"accountNonExpired\": true,\n" +
//                "\t\"accountNonLocked\": true,\n" +
//                "\t\"credentialsNonExpired\": true,\n" +
//                "\t\"enabled\": true\n" +
//                "}";
//        return JSONObject.fromObject(param);
        return principal;
    }

    /**
     * Create {@link GrantedAuthority} by a special role.
     *
     * @param roles the roles
     * @return a list of {@link GrantedAuthority}
     */
    private List<GrantedAuthority> createAuthorityList(final Collection<String> roles) {
        final List<GrantedAuthority> authorities = new ArrayList<>(roles.size());

        for (final String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
            // add spring security ROLE authority which is indicated by the
            // `ROLE_` prefix
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return authorities;
    }
}