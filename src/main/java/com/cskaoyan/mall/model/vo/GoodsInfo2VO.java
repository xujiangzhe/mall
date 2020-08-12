package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.SpecInfoBO;

import java.util.List;

public class GoodsInfo2VO {

    private String img;

    private String name;

    private String desc;

    private Integer typeId;

    private List<SpecInfoBO> specs;

    private Double unitPrice;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecInfoBO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecInfoBO> specs) {
        this.specs = specs;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public GoodsInfo2VO() {
    }

    public GoodsInfo2VO(String img, String name, String desc, Integer typeId, List<SpecInfoBO> specs, Double unitPrice) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
        this.specs = specs;
        this.unitPrice = unitPrice;
    }
}
