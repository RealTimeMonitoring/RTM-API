package com.rtm.api.domain.exception;

public class InvalidCredentialsException 
    extends 
        RuntimeException 
{
    public InvalidCredentialsException( String s ) 
    {
        super( s );
    }
}

