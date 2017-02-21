package com.roside.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.roside.mybatis.domain.ProjectInfo;

@Mapper
public interface ProjectInfoMapper extends Serializable {

    List<ProjectInfo> getProjectList();

}
