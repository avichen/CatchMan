package com.roside.security;

import com.roside.mybatis.domain.User;
import com.roside.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Eric on 2017-01-23.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("-------CustomAuthenticationProvider---------");


        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User checkedUser = (User) userService.getUserByNameAndPassword(user);
//        logger.debug("-------Check the username and password---------");
        if (checkedUser == null){
            throw new BadCredentialsException("Username or password is not currect.");
        }
//        logger.debug("-------Check the loadUserByUsername---------");
        UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(username);
//        logger.debug("-------Check the loadUserByUsername end---------");
        if (userDetails == null){
            throw new BadCredentialsException("Username not found.");
        }else{
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }
//
//        if (username.equals("admin") && password.equals("system")){
//            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
//        }else{
//            return null;
//        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
