package com.roside.security.entity;

import com.roside.mybatis.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by Eric on 2017-01-24.
 */
public class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
        );
    }
}
