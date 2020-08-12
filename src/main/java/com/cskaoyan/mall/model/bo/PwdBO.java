package com.cskaoyan.mall.model.bo;

public class PwdBO {

    private String id;

    private String oldPwd;

    private String newPwd;

    private String confirmPwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public PwdBO() {
    }

    public PwdBO(String id, String oldPwd, String newPwd, String confirmPwd) {
        this.id = id;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.confirmPwd = confirmPwd;
    }
}
