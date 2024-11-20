package com.rtm.api.infra.config.security;

import com.rtm.api.domain.exception.InvalidCredentialsException;
import com.rtm.api.domain.exception.NotFoundException;
import com.rtm.api.domain.exception.TokenBlacklistedException;
import com.rtm.api.infra.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class SecurityFilter
    extends
        OncePerRequestFilter
{
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final TokenBlackListService tokenBlackListService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
    {
        try 
        {
            String token = recoverToken( request );
            String login = tokenService.validateToken( token );
        
            if ( tokenBlackListService.isBlacklisted( token ) )
            {
                throw new TokenBlacklistedException( "The token is invalid or has been revoked" );
            }
            
            if ( login != null )
            {
                var user = userRepository.findByEmail( login ).orElseThrow( () -> new NotFoundException( "User not found" ) );
                var authority = Collections.singletonList( new SimpleGrantedAuthority( "ROLE_USER") ); // NÃO NECESSITAMOS DE CONTROLE DE ROLES
                var authentication = new UsernamePasswordAuthenticationToken( user, null, authority );
                
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
            
            filterChain.doFilter( request, response );   
        }
    
        catch ( TokenBlacklistedException ex ) 
        {
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            response.getWriter().write( "{\"message\": \"" + ex.getMessage() + "\"}" );
            response.setContentType( "application/json" );
        }
    }
    
    private String recoverToken( HttpServletRequest request )
    {
        String authorization = request.getHeader( "Authorization" );
        
        if ( authorization == null )
        {
            return null;
        }
        
        return authorization.replace( "Bearer ", "" );
    }
}
