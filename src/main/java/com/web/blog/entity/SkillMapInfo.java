package com.web.blog.entity;

public class SkillMapInfo {
    private int id = 0;
    private String name = null;
    private int teacherid = 0;

    public SkillMapInfo(int id, String name, int teacherid) {
        this.id = id;
        this.name = name;
        this.teacherid = teacherid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }
}
