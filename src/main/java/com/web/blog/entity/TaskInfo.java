package com.web.blog.entity;

import java.sql.Timestamp;

public class TaskInfo {
    private int  id = 0;
    private String designation = null;
    private String direction = null;
    private int teacher_id = 0;
    private int student_id = 0;
    private String charter = null;
    private Timestamp crt_ts = null;
    private Timestamp lm_ts = null;
    private int status = 0;
    private String review = null;
    private int cycle = 0;

    public TaskInfo(String designation, String direction, int teacher_id, int cycle) {
        this.designation = designation;
        this.direction = direction;
        this.teacher_id = teacher_id;
        this.cycle = cycle;
    }

    public TaskInfo(int student_id, int teacher_id, int id) {
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.id = id;
    }

    public TaskInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public Timestamp getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(Timestamp crt_ts) {
        this.crt_ts = crt_ts;
    }

    public Timestamp getLm_ts() {
        return lm_ts;
    }

    public void setLm_ts(Timestamp lm_ts) {
        this.lm_ts = lm_ts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
