package com.rtm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@CrossOrigin("*")
public class RealTimeMonitoringApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(RealTimeMonitoringApplication.class, args);
    }
    
    @Bean
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
