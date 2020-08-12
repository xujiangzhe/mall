package com.cskaoyan.mall.model;

public class Goods {

    private Integer id;

    private String img;

    private String name;

    private Double price;

    private Integer typeId;

    private Integer stockNum;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Goods() {
    }

    public Goods(Integer id, String name, String img, Integer typeId, Double price, Integer stockNum, String desc) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.typeId = typeId;
        this.price = price;
        this.stockNum = stockNum;
        this.desc = desc;
    }
}
