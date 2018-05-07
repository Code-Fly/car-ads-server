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
//        super.configure(http);
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                    .antMatcher("/**") // 捕捉所有路由
                .authorizeRequests()
                    .antMatchers("/oauth/**").permitAll()
                    .antMatchers("/assets/**").permitAll()
    //                .antMatchers("/oauth/logout").permitAll()
    //                .antMatchers("/oauth/exit").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth/login"))
                    .and()
                .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/oauth/login")
                    .failureUrl("/oauth/login?error=1")
                    .and()
//                .sessionManagement()
//                    .sessionAuthenticationErrorUrl("/oauth/login?error=2")
//                    .invalidSessionUrl("/oauth/login?error=3")
//                    .maximumSessions(1)
//                    .expiredUrl("/oauth/login?error=4")
//                    .maxSessionsPreventsLogin(false)
//                    .sessionRegistry(sessionRegistry())
//                    .and()

//                .and()
//                .logout().logoutUrl("/oauth/logout")
//                .logoutSuccessUrl("/oauth/exit")
//                .permitAll()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico");
    }

}
