package com.roside.service;

import com.roside.mybatis.domain.User;
import com.roside.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Created by Eric on 2017-01-17.
 */

@Service
public class UserService implements UserMapper{

    private static final String SALT = "$2a$10$necBOMlJucIQq.pEjXnQUe";

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id){
        return this.userMapper.getUserById(id);
    }

    public User getUserByMail(String username){
        return this.userMapper.getUserByMail(username);
    }

    public User getUserByNameAndPassword(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), SALT));
        return this.userMapper.getUserByNameAndPassword(user);
    }

    public int registerUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), SALT));
        return userMapper.registerUser(user);
    }
}
