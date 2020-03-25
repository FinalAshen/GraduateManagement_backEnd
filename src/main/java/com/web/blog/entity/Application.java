package com.web.blog.entity;

public class Application {
    private String id;
    private String teacher_id;
    private String student_id;
    private String status;
    private String dstatus;
    private String crt_ts;

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(String crt_ts) {
        this.crt_ts = crt_ts;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", status='" + status + '\'' +
                ", crt_ts='" + crt_ts + '\'' +
                '}';
    }
}
