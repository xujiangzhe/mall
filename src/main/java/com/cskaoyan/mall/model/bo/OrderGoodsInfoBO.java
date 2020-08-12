package com.cskaoyan.mall.model.bo;

public class OrderGoodsInfoBO {

    private Integer id;

    private String img;

    private String name;

    private Integer goodsDatailId;

    private String spec;

    private Double unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getGoodsDatailId() {
        return goodsDatailId;
    }

    public void setGoodsDatailId(Integer goodsDatailId) {
        this.goodsDatailId = goodsDatailId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderGoodsInfoBO() {
    }

    public OrderGoodsInfoBO(Integer id, String img, String name, Integer goodsDatailId, String spec, Double unitPrice) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.goodsDatailId = goodsDatailId;
        this.spec = spec;
        this.unitPrice = unitPrice;
    }
}
