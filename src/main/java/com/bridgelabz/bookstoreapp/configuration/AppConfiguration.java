package com.bridgelabz.bookstoreapp.configuration;

import com.bridgelabz.bookstoreapp.util.JMS;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public JWTToken jwtToken() {
        return new JWTToken();
    }
    @Bean
    public JMS emailSender() {
        return new JMS();
    }
}
