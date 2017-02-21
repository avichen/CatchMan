package com.roside.mybatis.domain;

import java.io.Serializable;

/**
 * Created by Eric on 2017-02-20.
 */
public class ProjectInfo implements Serializable{

    private static final long serialVersionUID = 6877215019148891197L;
    private Integer id;
    private String project_cn_name;
    private String project_en_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_cn_name() {
        return project_cn_name;
    }

    public void setProject_cn_name(String project_cn_name) {
        this.project_cn_name = project_cn_name;
    }

    public String getProject_en_name() {
        return project_en_name;
    }

    public void setProject_en_name(String project_en_name) {
        this.project_en_name = project_en_name;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "project_cn_name='" + project_cn_name + '\'' +
                ", project_en_name='" + project_en_name + '\'' +
                '}';
    }
}
