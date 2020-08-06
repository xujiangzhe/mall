package com.cskaoyan.mall.model.vo;

public class AdminLoginVO {

    private String token;

    private String name;

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public AdminLoginVO(String token, String name) {
        this.token = token;
        this.name = name;
    }
}
