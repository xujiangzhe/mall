package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.Reply2BO;

public class GoodsMsgVO {

    private Integer id;

    private String content;

    private String asker;

    private String time;

    private Reply2BO reply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Reply2BO getReply() {
        return reply;
    }

    public void setReply(Reply2BO reply) {
        this.reply = reply;
    }

    public GoodsMsgVO() {
    }

    public GoodsMsgVO(Integer id, String content, String asker, String time, Reply2BO reply) {
        this.id = id;
        this.content = content;
        this.asker = asker;
        this.time = time;
        this.reply = reply;
    }
}
