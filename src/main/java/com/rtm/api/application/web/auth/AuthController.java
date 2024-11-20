package com.rtm.api.application.web.auth;

import com.rtm.api.application.dto.request.LoginRequestDTO;
import com.rtm.api.application.dto.request.RegisterRequestDTO;
import com.rtm.api.application.dto.response.TokenResponseDTO;
import com.rtm.api.domain.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AuthController implements AuthApi 
{
    private AuthService authService;
    
    @Override
    public TokenResponseDTO authenticateUser( LoginRequestDTO loginRequestDTO ) {
        return authService.authenticateUser( loginRequestDTO );
    }
    
    @Override
    public TokenResponseDTO registerUser(RegisterRequestDTO registerRequestDTO) {
        return authService.registerUser( registerRequestDTO );
    }
}
