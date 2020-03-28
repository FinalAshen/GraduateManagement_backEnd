package com.web.blog.entity;

public class TeacherGroupInfo {
    private int id = 0;
    private int group_id = 0;
    private int teacher_id = 0;

    public TeacherGroupInfo(int id, int group_id, int teacher_id) {
        this.id = id;
        this.group_id = group_id;
        this.teacher_id = teacher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
