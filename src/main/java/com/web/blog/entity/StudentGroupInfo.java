package com.web.blog.entity;

public class StudentGroupInfo {
    private Integer id = 0;
    private Integer group_id = 0;
    private Integer student_id = 0;

    public StudentGroupInfo(Integer id, Integer group_id, Integer student_id) {
        this.id = id;
        this.group_id = group_id;
        this.student_id = student_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
}
