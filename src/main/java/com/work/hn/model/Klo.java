package com.work.hn.model;

import java.io.Serializable;

/**
 * @author xh.d
 * @date 2017/12/5 19:12
 */
public class Klo implements Serializable{
    private static final long serialVersionUID = 4451819909971513838L;
    private String id;
    private String name;
    private String email;
    private String tel;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Klo(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Klo() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
