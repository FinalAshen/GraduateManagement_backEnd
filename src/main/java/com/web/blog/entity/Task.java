package com.web.blog.entity;

public class Task {
    private String id;
    private String designation;
    private String direction;
    private String teacher_id;
    private String student_id;
    private String charter;
    private String crt_ts;
    private String lm_ts;
    private String status;
    private String review;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public String getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(String crt_ts) {
        this.crt_ts = crt_ts;
    }

    public String getLm_ts() {
        return lm_ts;
    }

    public void setLm_ts(String lm_ts) {
        this.lm_ts = lm_ts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", designation='" + designation + '\'' +
                ", direction='" + direction + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", charter='" + charter + '\'' +
                ", crt_ts='" + crt_ts + '\'' +
                ", lm_ts='" + lm_ts + '\'' +
                ", status='" + status + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
