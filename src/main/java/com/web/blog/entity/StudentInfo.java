package com.web.blog.entity;

public class StudentInfo {
    private int id = 0;
    private String name = null;
    private String password = null;
    private String major = null;
    private String sclass = null;
    private String cellphone = null;
    private int teacher_id = 0;
    private float t_result = 0;
    private float r_result = 0;
    private float total_result = 0;
    private int state = 0;
    private int s_state = 1;

    public StudentInfo(int id, String name, String major, String sclass, String cellphone, int teacher_id) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.sclass = sclass;
        this.cellphone = cellphone;
        this.teacher_id = teacher_id;
    }
    public StudentInfo() {

    }

    public int getS_state() {
        return s_state;
    }

    public void setS_state(int s_state) {
        this.s_state = s_state;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public float getT_result() {
        return t_result;
    }

    public void setT_result(float t_result) {
        this.t_result = t_result;
    }

    public float getR_result() {
        return r_result;
    }

    public void setR_result(float r_result) {
        this.r_result = r_result;
    }

    public float getTotal_result() {
        return total_result;
    }

    public void setTotal_result(float total_result) {
        this.total_result = total_result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
