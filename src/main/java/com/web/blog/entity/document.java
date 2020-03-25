package com.web.blog.entity;

public class document {
    private String id;
    private String designation;
    private double score;
    private String teacher_id;
    private String student_id;
    private String position;

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "document{" +
                "id='" + id + '\'' +
                ", designation='" + designation + '\'' +
                ", score='" + score + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
