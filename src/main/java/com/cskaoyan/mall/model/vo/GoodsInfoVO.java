package com.cskaoyan.mall.model.vo;

import com.cskaoyan.mall.model.bo.GoodsInfoBO;
import com.cskaoyan.mall.model.bo.SpecInfoBO;

import java.util.List;

public class GoodsInfoVO {

    private List<SpecInfoBO> specs;

    private GoodsInfoBO goods;

    public List<SpecInfoBO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecInfoBO> specs) {
        this.specs = specs;
    }

    public GoodsInfoBO getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoBO goods) {
        this.goods = goods;
    }

    public GoodsInfoVO(List<SpecInfoBO> specs, GoodsInfoBO goods) {
        this.specs = specs;
        this.goods = goods;
    }
}
