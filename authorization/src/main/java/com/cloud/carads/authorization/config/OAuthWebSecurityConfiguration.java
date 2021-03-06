package com.cloud.carads.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@Configuration
public class OAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//        http
//                .cors()
//                    .and()
//                .csrf()
//                    .disable()
//                    .antMatcher("/oauth/**")
//                .authorizeRequests()
//                    .antMatchers("/oauth/token").permitAll()
//                    .antMatchers("/oauth/authorize").permitAll()
//                    .antMatchers("/oauth/check_token").permitAll()
//                    .antMatchers("/oauth/confirm_access").permitAll()
//                    .antMatchers("/oauth/error").permitAll()
//                    .antMatchers("/oauth/login").permitAll()
//                    .antMatchers("/oauth/logout").permitAll()
//                    .antMatchers("/assets/**").permitAll()
//    //                .antMatchers("/oauth/logout").permitAll()
//    //                .antMatchers("/oauth/exit").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .exceptionHandling()
//                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth/login"))
//                    .and()
//                .formLogin()
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .loginPage("/oauth/login")
//                    .failureUrl("/oauth/login?error=1")
//                    .and()
////                .sessionManagement()
////                    .sessionAuthenticationErrorUrl("/oauth/login?error=2")
////                    .invalidSessionUrl("/oauth/login?error=3")
////                    .maximumSessions(1)
////                    .expiredUrl("/oauth/login?error=4")
////                    .maxSessionsPreventsLogin(false)
////                    .sessionRegistry(sessionRegistry())
////                    .and()
//
////                .and()
////                .logout().logoutUrl("/oauth/logout")
////                .logoutSuccessUrl("/oauth/exit")
////                .permitAll()
//                .httpBasic();
        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth/login"))
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/oauth/login")
                .failureUrl("/oauth/login?error=1")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico");
    }

}
