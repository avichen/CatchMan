package com.roside.service;

import com.roside.mybatis.domain.ProjectInfo;
import com.roside.mybatis.mapper.ProjectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInfoService{

    @Autowired
    private ProjectInfoMapper projectInfoMapper;


    public List<ProjectInfo> getProjectList(){
        return projectInfoMapper.getProjectList();
    }

}
