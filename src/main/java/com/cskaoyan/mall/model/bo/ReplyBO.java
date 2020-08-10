package com.cskaoyan.mall.model.bo;

public class ReplyBO {

    private Integer id;

    private String content;

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

    public ReplyBO() {
    }

    public ReplyBO(Integer id, String content) {
        this.id = id;
        this.content = content;
    }
}
