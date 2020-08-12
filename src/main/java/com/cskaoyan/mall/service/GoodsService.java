package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.AddGoodsBO;
import com.cskaoyan.mall.model.bo.AskGoodsMsgBO;
import com.cskaoyan.mall.model.bo.DeleteSpecBO;
import com.cskaoyan.mall.model.bo.ReplyBO;
import com.cskaoyan.mall.model.vo.*;

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

    List<Goods> searchGoods(String keyword);

    GoodsInfo2VO getGoodsInfo2(String id);

    List<GoodsMsgVO> getGoodsMsg(String id);

    CommentGoodsVO getGoodsComment(String id);

    void askGoodsMsg(AskGoodsMsgBO askGoodsMsgBO);
}
