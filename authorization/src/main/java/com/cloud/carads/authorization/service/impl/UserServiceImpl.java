package com.cloud.carads.authorization.service.impl;

import com.cloud.carads.authorization.service.IUserService;
import com.cloud.carads.commons.service.BaseService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements IUserService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("===================获取到token已进入自定义验证：" + username);
        List<String> roles = new ArrayList<>();

        return new User(username, new StandardPasswordEncoder().encode(username), createAuthorityList(roles));
    }

    /**
     * Create {@link GrantedAuthority} by a special role.
     *
     * @param roles the roles
     * @return a list of {@link GrantedAuthority}
     */
    public static List<GrantedAuthority> createAuthorityList(final Collection<String> roles) {
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
