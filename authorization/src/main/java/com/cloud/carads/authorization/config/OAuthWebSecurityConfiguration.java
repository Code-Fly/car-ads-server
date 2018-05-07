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
        http.cors()
                .and()
                .csrf().disable()
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
                .and().formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/oauth/login")
                .failureUrl("/oauth/login?error=1")
                .permitAll()
//                .and()
//                .logout().logoutUrl("/oauth/logout")
//                .logoutSuccessUrl("/oauth/exit")
//                .permitAll()
                .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico");
    }

}
