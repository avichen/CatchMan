package com.roside.security.service;

import java.io.Serializable;

/**
 * Created by Eric on 2017-01-22.
 */
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8347328083939863819L;


    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JwtAuthenticationRequest() {
        super();
    }


    public JwtAuthenticationRequest(String username, String password){
        this.setUsername(username);
        this.setPassword(password);

    }

}
