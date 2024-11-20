package com.rtm.api.domain.exception;

public class TokenBlacklistedException 
    extends
        RuntimeException
{
    public TokenBlacklistedException( String message )
    {
        super( message);
    }
}
