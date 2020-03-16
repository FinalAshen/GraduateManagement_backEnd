package com.web.blog.entity;

public class UserInfo {
    private String name = null;
    private String nickName = null;
    private String email = null;
    private String account = null;
    private String password = null;
    private String collectArticleNumber = null;
    private String categoryNumber = null;
    private  String fanNumber = null;
    private String commentNumber = null;
    private String accessNumber = null;
    private String likeNumber = null;
    private String registerTime = null;
    private  String lastOnlineTime = null;

    public UserInfo() {}
    public UserInfo(String name, String nickName, String email, String account, String password) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.account = account;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getCollectArticleNumber() {
        return collectArticleNumber;
    }

    public void setCollectArticleNumber(String collectArticleNumber) {
        this.collectArticleNumber = collectArticleNumber;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getFanNumber() {
        return fanNumber;
    }

    public void setFanNumber(String fanNumber) {
        this.fanNumber = fanNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.name + " " + this.nickName + " " + this.email + " " + this.account + " " + this.password;
    }
}
