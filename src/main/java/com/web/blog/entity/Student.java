package com.web.blog.entity;

import java.math.BigDecimal;

public class Student {
    private String id;
    private String name;
    private String password;
    private String major;
    private String sclass;
    private String cellphone;
    private String teacher_id;
    private double t_result;
    private double r_result;
    private BigDecimal total_result;
    private String state;
    private String r_state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getR_state() {
        return r_state;
    }

    public void setR_state(String r_state) {
        this.r_state = r_state;
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

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public double getT_result() {
        return t_result;
    }

    public void setT_result(double t_result) {
        this.t_result = t_result;
    }

    public double getR_result() {
        return r_result;
    }

    public void setR_result(double r_result) {
        this.r_result = r_result;
    }

    public BigDecimal getTotal_result() {
        return total_result;
    }

    public void setTotal_result(BigDecimal total_result) {
        this.total_result = total_result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                ", sclass='" + sclass + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", t_result=" + t_result +
                ", r_result=" + r_result +
                ", total_result=" + total_result +
                '}';
    }
}
