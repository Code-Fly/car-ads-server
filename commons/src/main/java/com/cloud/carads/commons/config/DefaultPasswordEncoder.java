package com.cloud.carads.commons.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return new StandardPasswordEncoder().encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return new StandardPasswordEncoder().matches(charSequence, s);
    }
}
