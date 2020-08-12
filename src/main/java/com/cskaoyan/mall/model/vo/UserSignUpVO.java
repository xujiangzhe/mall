package com.cskaoyan.mall.model.vo;

public class UserSignUpVO {

    private int code;

    private String token;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserSignUpVO() {
    }

    public UserSignUpVO(int code, String token, String name) {
        this.code = code;
        this.token = token;
        this.name = name;
    }
}
