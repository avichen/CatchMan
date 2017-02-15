package com.roside.security.service;

import java.io.Serializable;

/**
 * Created by Eric on 2017-01-22.
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 6016596836174000806L;

    private final String token;

    public JwtAuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
