package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.*;
import com.cskaoyan.mall.model.bo.*;
import com.cskaoyan.mall.model.vo.GoodsUpdateVO;
import com.cskaoyan.mall.model.vo.NoReplyMsgVO;
import com.cskaoyan.mall.model.vo.RepliedMsgVO;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select * from `type`", new BeanListHandler<Type>(Type.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeList;
    }

    @Override
    public List<Goods> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Goods> goodsList = null;
        try {
            goodsList = runner.query("select * from goods where typeId = ?",
                    new BeanListHandler<Goods>(Goods.class), Integer.parseInt(typeId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into goods values (null, ?, ?, ?, ?, ?, ?)",
                    goods.getName(),
                    goods.getImg(),
                    goods.getTypeId(),
                    goods.getPrice(),
                    goods.getStockNum(),
                    goods.getDescr());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getLastId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = (BigInteger) runner.query("select last_insert_id()", new ScalarHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public void addSpecs(List<AddSpecBO> specList, int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (AddSpecBO addSpecBO : specList) {
            try {
                runner.update("insert into spec values (null, ?, ?, ?, ?)",
                        addSpecBO.getSpecName(),
                        addSpecBO.getStockNum(),
                        addSpecBO.getUnitPrice(),
                        goodsId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void addType(Type type) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into type values (null, ?)", type.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public GoodsInfoBO goodsInfo(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GoodsInfoBO good = null;
        try {
            good = runner.query("select * from goods where id = ?",
                    new BeanHandler<GoodsInfoBO>(GoodsInfoBO.class), Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return good;
    }

    @Override
    public List<SpecInfoBO> specInfoList(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecInfoBO> list = null;
        try {
            list = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<SpecInfoBO>(SpecInfoBO.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Spec addSpec(Spec spec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into spec values (null, ?, ?, ?, ?)", spec.getSpecName(),
                    spec.getStockNum(), spec.getUnitPrice(), spec.getGoodsId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int id = getLastId();
        Spec sp = null;
        try {
            sp = runner.query("select * from spec where id = ?", new BeanHandler<Spec>(Spec.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sp;
    }

    @Override
    public void deleteSpec(DeleteSpecBO deleteSpecBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ? and specName = ?",
                    Integer.parseInt(deleteSpecBO.getGoodsId()), deleteSpecBO.getSpecName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateGoods(GoodsUpdateVO goodsUpdateVO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update goods set `name` = ?, typeId = ?, img = ?, `desc` = ? where id = ?",
                    goodsUpdateVO.getName(), goodsUpdateVO.getTypeId(), goodsUpdateVO.getImg(),
                    goodsUpdateVO.getDesc(), goodsUpdateVO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Spec spec : goodsUpdateVO.getSpecList()) {
            try {
                runner.update("update spec set specName = ?, stockNum = ?, unitPrice = ? where id = ?",
                        spec.getSpecName(), spec.getStockNum(), spec.getUnitPrice(), spec.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteGoods(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from goods where id = ?", Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<NoReplyMsgVO> noReplyMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Msg> msgList = null;
        try {
            msgList = runner.query("select * from msg where replycontent is null", new BeanListHandler<Msg>(Msg.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<NoReplyMsgVO> nrmList = new ArrayList<>();
        for (Msg msg : msgList) {
            String goods = null;
            try {
                goods = (String)runner.query("select name from goods where id = ?", new ScalarHandler(),
                        msg.getGoodsId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String user = null;
            try {
                user = (String)runner.query("select nickname from user where id = ?",
                        new ScalarHandler(), msg.getUserId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nrmList.add(new NoReplyMsgVO(msg.getId(), msg.getUserId(), msg.getGoodsId(),
                    msg.getContent(), msg.getState(), msg.getCreatetime(), new Name(goods), new Name(user)));
        }
        return nrmList;
    }

    @Override
    public List<RepliedMsgVO> repliedMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Msg> msgList = null;
        try {
            msgList = runner.query("select * from msg where replycontent is not null", new BeanListHandler<Msg>(Msg.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<RepliedMsgVO> rmList = new ArrayList<>();
        for (Msg msg : msgList) {
            String goods = null;
            try {
                goods = (String)runner.query("select name from goods where id = ?", new ScalarHandler(),
                        msg.getGoodsId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String user = null;
            try {
                user = (String)runner.query("select nickname from user where id = ?",
                        new ScalarHandler(), msg.getUserId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rmList.add(new RepliedMsgVO(msg.getId(), msg.getUserId(), msg.getGoodsId(), msg.getContent(),
                    msg.getReplyContent(), msg.getState(), msg.getCreatetime(), new Name(goods), new Name(user)));
        }
        return rmList;
    }

    @Override
    public void reply(ReplyBO replyBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update msg set replyContent = ? where id = ?", replyBO.getContent(), replyBO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
