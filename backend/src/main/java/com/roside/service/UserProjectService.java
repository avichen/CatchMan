package com.roside.service;

import com.roside.mybatis.domain.UserProject;
import com.roside.mybatis.mapper.UserProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectService{

    @Autowired
    private UserProjectMapper userProjectMapper;

    public int insert(UserProject pojo){
        return userProjectMapper.insert(pojo);
    }

    public int insertList(List<UserProject> pojos){
        return userProjectMapper.insertList(pojos);
    }

    public int update(UserProject pojo){
        return userProjectMapper.update(pojo);
    }

}
