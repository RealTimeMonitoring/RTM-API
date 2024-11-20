package com.rtm.api.domain.service;

import com.rtm.api.application.dto.request.LoginRequestDTO;
import com.rtm.api.application.dto.request.RegisterRequestDTO;
import com.rtm.api.application.dto.response.TokenResponseDTO;
import com.rtm.api.domain.exception.InvalidCredentialsException;
import com.rtm.api.domain.exception.NotFoundException;
import com.rtm.api.domain.mapper.UserMapper;
import com.rtm.api.domain.model.User;
import com.rtm.api.infra.config.security.TokenService;
import com.rtm.api.infra.repository.UserRepository;
import com.rtm.api.infra.util.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService 
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    
    public TokenResponseDTO authenticateUser( LoginRequestDTO loginRequestDTO )
    {
        Optional<User> user = userRepository.findByEmail( loginRequestDTO.email() );
        
        return user.filter( u -> passwordEncoder.matches( loginRequestDTO.password(), u.getPassword() ) )
                   .map( u -> new TokenResponseDTO( userMapper.toDto( u ), tokenService.generateToken( u ) ) )
                   .orElseThrow( () -> new NotFoundException( "Invalid email or password" ) );
    }
    
    public TokenResponseDTO registerUser(RegisterRequestDTO registerRequestDTO )
    {
        if ( !RegisterValidator.isEmailValid( registerRequestDTO.email() ) )
        {
            throw new InvalidCredentialsException( "Invalid email" );
        }
        
        Optional<User> u = userRepository.findByEmail( registerRequestDTO.email() );
        
        if ( u.isPresent() )
        {
            throw new NotFoundException( "User already exists" );
        }
        
        if ( !RegisterValidator.isPassValid( registerRequestDTO.password() ) )
        {
            throw new InvalidCredentialsException( "Invalid password" );
        }
        
        User user = new User();
        user.setName( registerRequestDTO.username() );
        user.setEmail( registerRequestDTO.email() );
        user.setPassword( passwordEncoder.encode( registerRequestDTO.password() ) );
        
         userRepository.save( user );
        
        return new TokenResponseDTO( userMapper.toDto( user ), tokenService.generateToken( user ) );
    }
}
