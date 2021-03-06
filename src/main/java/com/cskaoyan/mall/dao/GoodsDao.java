package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.*;
import com.cskaoyan.mall.model.vo.*;

import java.util.List;

public interface GoodsDao {
    List<Type> getType();

    List<Goods> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int getLastId();

    void addSpecs(List<AddSpecBO> specList, int goodsId);

    void addType(Type type);

    GoodsInfoBO goodsInfo(String id);

    List<SpecInfoBO> specInfoList(String id);

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
