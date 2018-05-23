package com.cloud.zuul.gateway.config.jwt;

import com.cloud.carads.commons.config.DefaultPasswordEncoder;
import com.cloud.zuul.gateway.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 *
 * @author zhaoxinguo on 2017/9/13.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class JwtWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.header}")
    public String JWT_HEADER;

    @Value("${jwt.secret}")
    public String JWT_SECRET;

    @Value("${jwt.expire}")
    public Long JWT_EXPIRE;

    @Autowired
    private IUserService userService;

    @Autowired
    private DefaultPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager(), JWT_HEADER, JWT_SECRET, JWT_EXPIRE))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), JWT_HEADER, JWT_SECRET));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}