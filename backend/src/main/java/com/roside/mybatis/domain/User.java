package com.roside.mybatis.domain;

import java.io.Serializable;

/**
 * Created by Eric on 2017-01-17.
 */
public class User implements Serializable {


    private static final long serialVersionUID = 3035303850009432731L;

    private Integer id;
    private String username;
    private String password;
    private String regist_time;
    private String last_act_ip;
    private String last_act_time;
    private String status;
    private String authorities;

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegist_time() {
        return regist_time;
    }

    public void setRegist_time(String regist_time) {
        this.regist_time = regist_time;
    }

    public String getLast_act_ip() {
        return last_act_ip;
    }

    public void setLast_act_ip(String last_act_ip) {
        this.last_act_ip = last_act_ip;
    }

    public String getLast_act_time() {
        return last_act_time;
    }

    public void setLast_act_time(String last_act_time) {
        this.last_act_time = last_act_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
