package com.roside.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import com.roside.mybatis.domain.UserProject;
@Mapper
public interface UserProjectMapper extends Serializable {

    int insert(@Param("pojo") UserProject pojo);

    int insertList(@Param("pojos") List< UserProject> pojo);

    int update(@Param("pojo") UserProject pojo);

}
