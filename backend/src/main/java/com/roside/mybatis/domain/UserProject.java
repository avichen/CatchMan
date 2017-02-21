package com.roside.mybatis.domain;

import java.io.Serializable;

/**
 * Created by Eric on 2017-02-20.
 */
public class UserProject implements Serializable{
//    private static final long serialVersionUID = -4214706979114250826L;

    private Integer id;
    private Integer user_id;
    private Integer project_id;
    private String update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

}
