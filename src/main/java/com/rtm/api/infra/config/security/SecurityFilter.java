package com.rtm.api.infra.config.security;

import com.rtm.api.domain.exception.NotFoundException;
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
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
    {
        String token = recoverToken( request );
        String login = tokenService.validateToken( token );
        
        if ( login != null )
        {
            var user = userRepository.findByEmail( login ).orElseThrow( () -> new NotFoundException( "User not found" ) );
            var authority = Collections.singletonList( new SimpleGrantedAuthority( "ROLE_USER") ); // NÃO NECESSITAMOS DE CONTROLE DE ROLES
            var authentication = new UsernamePasswordAuthenticationToken( user, null, authority );
            
            SecurityContextHolder.getContext().setAuthentication( authentication );
        }
        
        filterChain.doFilter( request, response );   
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
