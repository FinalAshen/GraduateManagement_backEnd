package com.web.blog.entity;

public class Application {
    private int  id;
    private int teacher_id;
    private int student_id;
    private int dstatus;
    private int status;
    private String crt_ts;

    public int getDstatus() {
        return dstatus;
    }

    public void setDstatus(int dstatus) {
        this.dstatus = dstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(String crt_ts) {
        this.crt_ts = crt_ts;
    }
}
