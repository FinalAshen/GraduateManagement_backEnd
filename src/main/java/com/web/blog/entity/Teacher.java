package com.web.blog.entity;
import java.util.List;
public class Teacher {
    private int id;
    private String name;
    private String password;
    private String cellphone;
    private String t_state;
    private List<Skill_map> skill_maps;
    public String getT_state() {
        return t_state;
    }

    public void setT_state(String t_state) {
        this.t_state = t_state;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public List<Skill_map> getSkill_maps() {
        return skill_maps;
    }

    public void setSkill_maps(List<Skill_map> skill_maps) {
        this.skill_maps = skill_maps;
    }
    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
