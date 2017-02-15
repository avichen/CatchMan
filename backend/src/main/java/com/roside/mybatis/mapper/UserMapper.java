package com.roside.mybatis.mapper;

import com.roside.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * Created by Eric on 2017-01-17.
 */

@Mapper
public interface UserMapper extends Serializable {

    User getUserById(int id);

    User getUserByMail(String email);

    User getUserByNameAndPassword(User user);
}
