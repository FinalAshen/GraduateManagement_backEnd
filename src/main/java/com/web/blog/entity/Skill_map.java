package com.web.blog.entity;

public class Skill_map {
    private int  id;
    private String name;
    private int teacherid;

    public int getId() {
        return id;
    }

    public int getTeacherid() {
        return teacherid;
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

    public void setTeacherid(int teacherid) {
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
