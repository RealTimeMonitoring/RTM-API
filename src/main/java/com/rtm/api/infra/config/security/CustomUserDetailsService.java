package com.rtm.api.infra.config.security;

import com.rtm.api.domain.model.User;
import com.rtm.api.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        User user = userRepository.findByEmail( username ).orElseThrow( () -> new UsernameNotFoundException( "User not found" ) );
        
        return new org.springframework.security.core.userdetails.User( user.getEmail(), user.getPassword(), new ArrayList<>() );
    }
}
