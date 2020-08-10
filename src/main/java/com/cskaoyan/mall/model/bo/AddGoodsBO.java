package com.cskaoyan.mall.model.bo;

import java.util.List;

//用于添加商品
public class AddGoodsBO {

    private String name;

    private String typeId;

    private String img;

    private String desc;

    private List<AddSpecBO> specList;

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

    public List<AddSpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<AddSpecBO> specList) {
        this.specList = specList;
    }
}
