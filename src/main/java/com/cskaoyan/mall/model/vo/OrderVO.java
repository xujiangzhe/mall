package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.Id;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;

import java.util.List;

public class OrderVO {

    private Integer id;

    private Integer amount;

    private Integer num;

    private Integer goodsDetailId;

    private Integer state;

    private String goods;

    private List<Spec> spec;

    private List<Type> states;

    private Id curState;

    private Id curSpec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<Spec> getSpec() {
        return spec;
    }

    public void setSpec(List<Spec> spec) {
        this.spec = spec;
    }

    public List<Type> getStates() {
        return states;
    }

    public void setStates(List<Type> states) {
        this.states = states;
    }

    public Id getCurState() {
        return curState;
    }

    public void setCurState(Id curState) {
        this.curState = curState;
    }

    public Id getCurSpec() {
        return curSpec;
    }

    public void setCurSpec(Id curSpec) {
        this.curSpec = curSpec;
    }

    public OrderVO(Integer id, Integer amount, Integer num, Integer goodsDetailId, Integer state, String goods, List<Spec> spec, List<Type> states, Id curState, Id curSpec) {
        this.id = id;
        this.amount = amount;
        this.num = num;
        this.goodsDetailId = goodsDetailId;
        this.state = state;
        this.goods = goods;
        this.spec = spec;
        this.states = states;
        this.curState = curState;
        this.curSpec = curSpec;
    }
}
