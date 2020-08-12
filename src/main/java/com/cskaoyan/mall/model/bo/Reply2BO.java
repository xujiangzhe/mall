package com.cskaoyan.mall.model.bo;

public class Reply2BO {

    private String content;

    private String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Reply2BO() {
    }

    public Reply2BO(String content, String time) {
        this.content = content;
        this.time = time;
    }
}
