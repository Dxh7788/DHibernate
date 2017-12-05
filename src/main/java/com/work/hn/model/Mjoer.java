package com.work.hn.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xh.d
 * @date 2017/12/5 15:43
 */
public class Mjoer implements Serializable{
    private static final long serialVersionUID = 8823178623487219680L;
    private String id;
    private String name;
    private String pwd;
    private Map<String,String> answers;


    /**minor constructor*/
    public Mjoer() {
    }

    /**full constructor*/
    public Mjoer(String name, String pwd, Map<String, String> answers) {
        this.name = name;
        this.pwd = pwd;
        this.answers = answers;
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

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
