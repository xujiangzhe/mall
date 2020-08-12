package com.cskaoyan.mall.model.bo;

public class UserNameBO {

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserNameBO() {
    }

    public UserNameBO(String nickname) {
        this.nickname = nickname;
    }
}
