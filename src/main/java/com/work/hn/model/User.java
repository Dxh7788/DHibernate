package com.work.hn.model;

import java.io.Serializable;

/**
 * 实体类User
 * */
public class User implements Serializable{
    private String id;
    private String name;
    private String pwd;

    private Namer namer;

    public User(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Namer getNamer() {
        return namer;
    }

    public void setNamer(Namer namer) {
        this.namer = namer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", namer=" + namer +
                '}';
    }
}
