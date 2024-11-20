package com.rtm.api.infra.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration 
{
    private final SecurityFilter securityFilter;
    
     private static final String[] PERMITTED_PATTERNS = {
       "/auth/login",
       "/auth/register",
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf( AbstractHttpConfigurer::disable )
                .sessionManagement( sessionManagement -> sessionManagement.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .authorizeHttpRequests( requests -> requests.requestMatchers( PERMITTED_PATTERNS ).permitAll().anyRequest().permitAll())
                .addFilterBefore( securityFilter, UsernamePasswordAuthenticationFilter.class );
                
        return http.build();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration ) throws Exception 
    {
        return  authenticationConfiguration.getAuthenticationManager();
    }
}
