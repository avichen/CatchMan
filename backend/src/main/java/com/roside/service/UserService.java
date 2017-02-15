package com.roside.service;

import com.roside.mybatis.entity.User;
import com.roside.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Eric on 2017-01-17.
 */

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id){
        return this.userMapper.getUserById(id);
    }

    public User getUserByMail(String username){
        return this.userMapper.getUserByMail(username);
    }

    public User getUserByNameAndPassword(User user){
        return this.userMapper.getUserByNameAndPassword(user);
    }
}
