package com.work.hn.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xh.d
 * @date 2017/12/5 19:10
 */
public class Question implements Serializable {
    private static final long serialVersionUID = -2237775879653902340L;
    private String id;
    private String name;
    private Map<String,Klo> klos = new HashMap<String,Klo>();

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

    public Map<String, Klo> getKlos() {
        return klos;
    }

    public void setKlos(Map<String, Klo> klos) {
        this.klos = klos;
    }

    public Question(String id, String name, Map<String, Klo> klos) {
        this.id = id;
        this.name = name;
        this.klos = klos;
    }

    public Question() {
    }
}
