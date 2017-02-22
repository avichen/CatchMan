package com.roside.security.service;

import com.roside.mybatis.domain.User;
import com.roside.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2017-01-22.
 */
@Service
@Cacheable
public class UserDetailsServiceImpl implements UserDetailsService{


    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    @Cacheable(value = "userCache")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("--------------loadUserByUsername-----------------");
        logger.debug("-----------------------login: " + username);
        final User user = userService.getUserByMail(username);
            if (user == null){
                throw new UsernameNotFoundException("no user found");
            }
            final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            authorities.add(new SimpleGrantedAuthority("USER"));
            user.setAuthorities("1");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
