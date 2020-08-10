package com.cskaoyan.mall.model;

public class Goods {

    private Integer id;

    private String name;

    private String img;

    private Integer typeId;

    private Double price;

    private Integer stockNum;

    private String descr;

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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Goods() {
    }

    public Goods(Integer id, String name, String img, Integer typeId, Double price, Integer stockNum, String descr) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.typeId = typeId;
        this.price = price;
        this.stockNum = stockNum;
        this.descr = descr;
    }
}
