package com.rtm.api.infra.util;

import java.util.regex.Pattern;

public class RegisterValidator 
{
//    private static final Pattern PATTERN = Pattern.compile( "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,}$" );
    private static final Pattern EMAIL_PATTERN = Pattern.compile( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" );

    public static boolean isPassValid( String password ) 
    {
        if ( password == null ) 
        {
            return false;
        }

        return password.length() >= 3;
    }

    public static boolean isEmailValid( String email ) 
    {
        if ( email == null ) 
        {
            return false;
        }
        
        return EMAIL_PATTERN.matcher( email ).matches();
    }
}
