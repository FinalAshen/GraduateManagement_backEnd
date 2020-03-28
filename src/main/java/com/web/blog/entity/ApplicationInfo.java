package com.web.blog.entity;

import java.sql.Timestamp;
import java.util.Date;

public class ApplicationInfo {
    private int id = 0;
    private int teacher_id = 0;
    private int student_id = 0;
    private int dstatus = 0;
    private int status = 0;
    private Timestamp crt_ts = null;

    public ApplicationInfo(int id, int teacher_id, int student_id, int dstatus, int status, Timestamp crt_ts) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.dstatus = dstatus;
        this.status = status;
        this.crt_ts = crt_ts;
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

    public int getDstatus() {
        return dstatus;
    }

    public void setDstatus(int dstatus) {
        this.dstatus = dstatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(Timestamp crt_ts) {
        this.crt_ts = crt_ts;
    }
}
