package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.AddGoodsBO;
import com.cskaoyan.mall.model.bo.DeleteSpecBO;
import com.cskaoyan.mall.model.bo.ReplyBO;
import com.cskaoyan.mall.model.vo.GoodsInfoVO;
import com.cskaoyan.mall.model.vo.GoodsUpdateVO;
import com.cskaoyan.mall.model.vo.NoReplyMsgVO;
import com.cskaoyan.mall.model.vo.RepliedMsgVO;

import java.util.List;

public interface GoodsService {
    List<Type> getType();

    List<Goods> getGoodsByType(String typeId);

    void addGoods(AddGoodsBO addGoodsBO);

    void addType(Type type);

    GoodsInfoVO getGoodsInfo(String id);

    Spec addSpec(Spec spec);

    void deleteSpec(DeleteSpecBO deleteSpecBO);

    void updateGoods(GoodsUpdateVO goodsUpdateVO);

    void deleteGoods(String id);

    List<NoReplyMsgVO> noReplyMsg();

    List<RepliedMsgVO> repliedMsg();

    void reply(ReplyBO replyBO);
}
