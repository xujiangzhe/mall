package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.Spec;

import java.util.List;

public class GoodsUpdateVO {

    private int id;

    private String name;

    private String typeId;

    private String img;

    private String desc;

    private List<Spec> specList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Spec> getSpecList() {
        return specList;
    }

    public void setSpecList(List<Spec> specList) {
        this.specList = specList;
    }

    public GoodsUpdateVO(int id, String name, String typeId, String img, String desc, List<Spec> specList) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.img = img;
        this.desc = desc;
        this.specList = specList;
    }
}
