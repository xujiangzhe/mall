package com.cskaoyan.mall.model.bo;

public class DeleteSpecBO {

    private String goodsId;

    private String specName;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public DeleteSpecBO(String goodsId, String specName) {
        this.goodsId = goodsId;
        this.specName = specName;
    }
}
