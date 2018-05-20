package com.cloud.carads.authorization.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.exception.AuthorizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/oauth")
public class UserController extends BaseController {
    @Autowired
    private TokenStore tokenStore;

    @RequestMapping("/me")
    public User user(HttpServletRequest request) throws AuthorizationNotFoundException {
        String authHeader = request.getHeader("Authorization");
        logger.info("Authorization: " + authHeader);
        if (authHeader != null) {
            String tokenValue = authHeader.toLowerCase().replace("bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
            return (User) authentication.getPrincipal();
        } else {
            throw new AuthorizationNotFoundException();
        }
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