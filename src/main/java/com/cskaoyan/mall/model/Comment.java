package com.cskaoyan.mall.model;

public class Comment {

    private Integer id;

    private Integer userId;

    private Double score;

    private String specName;

    private String comment;

    private String time;

    private Integer goodsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Comment() {
    }

    public Comment(Integer id, Integer userId, Double score, String specName, String comment, String time, Integer goodsId) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.goodsId = goodsId;
    }
}
