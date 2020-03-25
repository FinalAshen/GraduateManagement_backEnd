package com.web.blog.entity;

public class Inform {
    private String id;
    private String title;
    private String content;
    private String creator;
    private String begin_ts;
    private String end_ts;
    private String t_group;
    private String s_group;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBegin_ts() {
        return begin_ts;
    }

    public void setBegin_ts(String begin_ts) {
        this.begin_ts = begin_ts;
    }

    public String getEnd_ts() {
        return end_ts;
    }

    public void setEnd_ts(String end_ts) {
        this.end_ts = end_ts;
    }

    public String getT_group() {
        return t_group;
    }

    public void setT_group(String t_group) {
        this.t_group = t_group;
    }

    public String getS_group() {
        return s_group;
    }

    public void setS_group(String s_group) {
        this.s_group = s_group;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", begin_ts='" + begin_ts + '\'' +
                ", end_ts='" + end_ts + '\'' +
                ", t_group='" + t_group + '\'' +
                ", s_group='" + s_group + '\'' +
                '}';
    }
}
