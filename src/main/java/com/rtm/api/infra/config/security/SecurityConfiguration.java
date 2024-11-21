package com.rtm.api.infra.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration 
{
    private final SecurityFilter securityFilter;
    
     private static final String[] PERMITTED_PATTERNS = {
       "/auth/**",
       "/error",
       "/data/heatmap",
       "/category/heatmap",
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http , TokenBlackListService blacklist ) throws Exception
    {
        http.csrf( AbstractHttpConfigurer::disable ).cors( c -> c.configurationSource(corsConfigurationSource()))
                .sessionManagement( sessionManagement -> sessionManagement.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .authorizeHttpRequests( requests -> requests.requestMatchers( PERMITTED_PATTERNS )
                                                            .permitAll()
                                                            .anyRequest()
                                                            .authenticated() )
                .logout( logout -> logout.logoutUrl( "/auth/logout" )
                                         .logoutSuccessHandler( ( request, response, authentication ) -> {
                                                                  String token = this.recoverToken( request );
                                                                  
                                                                  if ( token != null )
                                                                  {
                                                                    blacklist.addToBlacklist( token );
                                                                  }
                                                                  
                                                                  response.setStatus( HttpServletResponse.SC_OK );
                                                                  response.getWriter().write( "Logout bem-sucedido!" );
                                                              } ) )
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

   /**
    * Se continuar dando ruim, coloca o ip ali http://192.168.0.x:xxxx
    **/
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( List.of( "*" ) );
        configuration.setAllowedMethods( Arrays.asList( "GET", "POST", "PUT", "DELETE", "OPTIONS" ) );
        configuration.setAllowedHeaders( List.of( "*" ) );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source; 
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
