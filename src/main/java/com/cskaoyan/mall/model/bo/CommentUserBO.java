package com.cskaoyan.mall.model.bo;

public class CommentUserBO {

    private UserNameBO user;

    private Double score;

    private Integer id;

    private String specName;

    private String comment;

    private String time;

    private Integer userId;

    public UserNameBO getUser() {
        return user;
    }

    public void setUser(UserNameBO user) {
        this.user = user;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CommentUserBO() {
    }

    public CommentUserBO(UserNameBO user, Double score, Integer id, String specName, String comment, String time, Integer userId) {
        this.user = user;
        this.score = score;
        this.id = id;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
    }
}
