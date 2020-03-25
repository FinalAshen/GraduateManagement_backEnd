package com.web.blog.entity;

public class Skill_map {
    private int  id;
    private String name;
    private String teacherid;

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

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    @Override
    public String toString() {
        return "Skill_map{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacherid='" + teacherid + '\'' +
                '}';
    }
}
