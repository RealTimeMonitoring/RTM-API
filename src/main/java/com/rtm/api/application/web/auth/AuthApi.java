package com.rtm.api.application.web.auth;

import com.rtm.api.application.dto.request.LoginRequestDTO;
import com.rtm.api.application.dto.request.RegisterRequestDTO;
import com.rtm.api.application.dto.response.TokenResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/auth")
public interface AuthApi 
{
    @PostMapping("/login")
    @ResponseStatus( HttpStatus.OK )
    TokenResponseDTO authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO);
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    TokenResponseDTO registerUser(@RequestBody RegisterRequestDTO registerRequestDTO);
}
