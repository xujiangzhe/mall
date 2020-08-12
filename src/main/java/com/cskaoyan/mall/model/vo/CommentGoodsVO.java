package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.CommentUserBO;

import java.util.ArrayList;
import java.util.List;

public class CommentGoodsVO {

    private List<CommentUserBO> commntList;

    private Double rate;

    public List<CommentUserBO> getCommntList() {
        return commntList;
    }

    public void setCommntList(List<CommentUserBO> commntList) {
        this.commntList = commntList;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public CommentGoodsVO() {
    }

    public CommentGoodsVO(List<CommentUserBO> commntList, Double rate) {
        this.commntList = commntList;
        this.rate = rate;
    }
}
