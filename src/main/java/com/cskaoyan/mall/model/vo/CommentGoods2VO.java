package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.CommentUserBO;

import java.util.ArrayList;
import java.util.List;

public class CommentGoods2VO {

    private List<CommentUserBO> commntList;

    private String rate;

    public List<CommentUserBO> getCommntList() {
        return commntList;
    }

    public void setCommntList(List<CommentUserBO> commntList) {
        this.commntList = commntList;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public CommentGoods2VO(String rate) {
        this.commntList = new ArrayList<>();
        this.rate = rate;
    }
}
