package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.OrderGoodsInfoBO;

public class OrderGoodsInfoVO {

    private Integer id;

    private Integer state;

    private Integer goodsNum;

    private Double amount;

    private Integer goodsDetailId;

    private String createtime;

    private boolean hasComment;

    private OrderGoodsInfoBO goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

    public OrderGoodsInfoBO getGoods() {
        return goods;
    }

    public void setGoods(OrderGoodsInfoBO goods) {
        this.goods = goods;
    }

    public OrderGoodsInfoVO() {
    }

    public OrderGoodsInfoVO(Integer id, Integer state, Integer goodsNum, Double amount, Integer goodsDetailId, String createtime, boolean hasComment, OrderGoodsInfoBO goods) {
        this.id = id;
        this.state = state;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.goodsDetailId = goodsDetailId;
        this.createtime = createtime;
        this.hasComment = hasComment;
        this.goods = goods;
    }
}
