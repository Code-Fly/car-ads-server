package com.cloud.carads.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@Configuration
public class OAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable();
        http.cors()
                .and()
                .antMatcher("/**") // 捕捉所有路由
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/index"))
                .and().formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/index")
                .permitAll()
                .and().httpBasic();
//        http.authorizeRequests().antMatchers("/oauth/**").permitAll();
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginPage("/index")
//                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico");
    }

}
