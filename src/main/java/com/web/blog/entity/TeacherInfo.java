package com.web.blog.entity;

public class TeacherInfo {
    private Integer id = 0;
    private String name = null;
    private String password = null;
    private String cellphone = null;
    private Integer t_state = 1;

    public TeacherInfo(Integer id, String name, String password, String cellphone, Integer t_state) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cellphone = cellphone;
        this.t_state = t_state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getT_state() {
        return t_state;
    }

    public void setT_state(Integer t_state) {
        this.t_state = t_state;
    }
}
