package com.example.userservice.core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptedUtil {

    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
