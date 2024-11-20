package com.rtm.api.infra.config.security;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlackListService 
{

    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();
 
    public void addToBlacklist( String token ) 
    {
        blacklist.add( token );
    }
    
    public boolean isBlacklisted( String token ) 
    {
        return blacklist.contains( token );
    }
}
