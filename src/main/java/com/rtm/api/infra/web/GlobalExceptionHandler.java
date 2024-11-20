package com.rtm.api.infra.web;

import com.rtm.api.domain.exception.InvalidCredentialsException;
import com.rtm.api.domain.exception.InvalidPasswordException;
import com.rtm.api.domain.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler( RuntimeException.class )
    public ResponseEntity<Object> handleRuntimeException( final RuntimeException e )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
    
    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Object> handleNotFoundException( final NotFoundException e )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND );
    }
    
    @ExceptionHandler( InvalidPasswordException.class )
    public ResponseEntity<Object> handleInvalidPasswordException( final InvalidPasswordException e )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.UNAUTHORIZED );
    }
    
    @ExceptionHandler( InvalidCredentialsException.class )
    public ResponseEntity<Object> handleInvalidCredentialsException( final InvalidCredentialsException e )
    {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
