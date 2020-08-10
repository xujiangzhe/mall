package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.Name;

public class NoReplyMsgVO {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private Integer state;

    private String createtime;

    private Name goods;

    private Name user;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Name getGoods() {
        return goods;
    }

    public void setGoods(Name goods) {
        this.goods = goods;
    }

    public Name getUser() {
        return user;
    }

    public void setUser(Name user) {
        this.user = user;
    }

    public NoReplyMsgVO() {
    }

    public NoReplyMsgVO(Integer id, Integer userId, Integer goodsId, String content, Integer state, String createtime, Name goods, Name user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }
}
