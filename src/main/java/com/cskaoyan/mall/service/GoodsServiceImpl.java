package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.GoodsDao;
import com.cskaoyan.mall.dao.GoodsDaoImpl;
import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.*;
import com.cskaoyan.mall.model.vo.GoodsInfoVO;
import com.cskaoyan.mall.model.vo.GoodsUpdateVO;
import com.cskaoyan.mall.model.vo.NoReplyMsgVO;
import com.cskaoyan.mall.model.vo.RepliedMsgVO;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public List<Goods> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
    * 1.将good信息保存到goods表 ———> 需要处理最小price，最大stockNum
    * 2.将spec信息保存到spec表 ——-> goodsId
    **/
    //TODO
    @Override
    public void addGoods(AddGoodsBO addGoodsBO) {
        List<AddSpecBO> specList = addGoodsBO.getSpecList();
        double price = Double.MAX_VALUE;
        int stockNum = 0;
        for (AddSpecBO addSpecBO : specList) {
            if (price > Double.parseDouble(addSpecBO.getUnitPrice())) {
                price = Double.parseDouble(addSpecBO.getUnitPrice());
            }
            if (stockNum < Integer.parseInt(addSpecBO.getStockNum())) {
                stockNum = Integer.parseInt((addSpecBO.getStockNum()));
            }
        }
        Goods goods = new Goods(null, addGoodsBO.getName(), addGoodsBO.getImg(),
                Integer.parseInt(addGoodsBO.getTypeId()), price, stockNum, addGoodsBO.getDesc());
        goodsDao.addGoods(goods);
        //goods表插入以后，会生成一个goodsId。
        //我们需要这个goodsId来插入规格表
        int goodsId = goodsDao.getLastId();
        goodsDao.addSpecs(specList, goodsId);
    }

    @Override
    public void addType(Type type) {
        goodsDao.addType(type);
    }

    @Override
    public GoodsInfoVO getGoodsInfo(String id) {
        List<SpecInfoBO> specList = goodsDao.specInfoList(id);
        GoodsInfoBO good = goodsDao.goodsInfo(id);
        return new GoodsInfoVO(specList, good);
    }

    @Override
    public Spec addSpec(Spec spec) {
        return goodsDao.addSpec(spec);
    }

    @Override
    public void deleteSpec(DeleteSpecBO deleteSpecBO) {
        goodsDao.deleteSpec(deleteSpecBO);
    }

    @Override
    public void updateGoods(GoodsUpdateVO goodsUpdateVO) {
        goodsDao.updateGoods(goodsUpdateVO);
    }

    @Override
    public void deleteGoods(String id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public List<NoReplyMsgVO> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public List<RepliedMsgVO> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public void reply(ReplyBO replyBO) {
        goodsDao.reply(replyBO);
    }

}
