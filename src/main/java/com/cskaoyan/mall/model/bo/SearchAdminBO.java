package com.cskaoyan.mall.model.bo;

public class SearchAdminBO {

    private String email;

    private String nickname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public SearchAdminBO(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
