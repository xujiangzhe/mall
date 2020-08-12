package com.cskaoyan.mall.model.bo;

public class AskGoodsMsgBO {

    private String token;

    private String msg;

    private String goodsId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public AskGoodsMsgBO() {
    }

    public AskGoodsMsgBO(String token, String msg, String goodsId) {
        this.token = token;
        this.msg = msg;
        this.goodsId = goodsId;
    }
}
