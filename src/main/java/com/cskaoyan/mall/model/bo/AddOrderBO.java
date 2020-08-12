package com.cskaoyan.mall.model.bo;

public class AddOrderBO {

    private String token;

    private Integer goodsDetailId;

    private Integer num;

    private Integer state;

    private Integer amount;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public AddOrderBO() {
    }

    public AddOrderBO(String token, Integer goodsDetailId, Integer num, Integer state, Integer amount) {
        this.token = token;
        this.goodsDetailId = goodsDetailId;
        this.num = num;
        this.state = state;
        this.amount = amount;
    }
}
