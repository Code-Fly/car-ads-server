package com.cloud.carads.authorization.service.impl;

import com.cloud.carads.authorization.entity.CAccountInfo;
import com.cloud.carads.authorization.service.IAccountService;
import com.cloud.carads.authorization.service.IUserService;
import com.cloud.carads.commons.entity.DataGrid;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.commons.service.BaseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements IUserService {
    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Username " + username + " is logging in");

        ErrorMsg response;
        try {
            response = accountService.getUserByUserName(username);
        } catch (Exception e) {
            logger.error("Get user failed", e);
            throw e;
        }
        if (!response.getErrcode().equals(Error.SUCCESS.getValue())) {
            logger.error(response.getErrmsg());
            throw new UsernameNotFoundException(response.getErrmsg());
        } else {
            DataGrid dataGrid = new Gson().fromJson(new Gson().toJson(response.getData()), DataGrid.class);

            List<CAccountInfo> users = new Gson().fromJson(new Gson().toJson(dataGrid.getRows()), new TypeToken<List<CAccountInfo>>() {
            }.getType());

            if (users.size() != 1) {
                logger.error("Username " + username + " not found");
                throw new UsernameNotFoundException("Username " + username + " not found");
            } else {
                CAccountInfo user = users.get(0);
                if (user.getFlag() == 5) {
                    logger.error("Username is disabled");
                    throw new UsernameNotFoundException("Username " + username + " is disabled");
                } else {
                    List<String> roles = new ArrayList<>();
                    return new User(username, user.getPassword(), createAuthorityList(roles));
                }
            }
        }

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
