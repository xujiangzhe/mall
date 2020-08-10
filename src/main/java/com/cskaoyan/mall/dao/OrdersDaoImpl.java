package com.cskaoyan.mall.dao;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.mall.model.Id;
import com.cskaoyan.mall.model.Orders;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.PageOrdersVO;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public Integer getTotalCount(PageOrdersBO pageOrdersBO) {
        Map map = getDynamicSqlAndParams(pageOrdersBO);
        String sql = (String)map.get("sql");
        List params = (List) map.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long)runner.query("select count(id) from orders " + sql, new ScalarHandler(),
                    params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query.intValue();
    }

    private Map getDynamicSqlAndParams(PageOrdersBO pageOrdersBO) {
        String baseSql = " where 1 = 1";
        List params = new ArrayList();
        if (!StringUtils.isEmpty(pageOrdersBO.getAddress())) {
            baseSql += " and address like ?";
            params.add("%" + pageOrdersBO.getAddress() + "%");
        }
        if (!StringUtils.isEmpty(pageOrdersBO.getGoods())) {
            baseSql += " and goods like ?";
            params.add("%" + pageOrdersBO.getGoods() + "%");
        }
        if (!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit1())) {
            baseSql += " and amount <= ?";
            params.add(pageOrdersBO.getMoneyLimit1());
        }
        if (!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit2())) {
            baseSql += " and amount >= ?";
            params.add(pageOrdersBO.getMoneyLimit2());
        }
        if (!StringUtils.isEmpty(pageOrdersBO.getId())) {
            baseSql += " and id = ?";
            params.add(pageOrdersBO.getId());
        }
        if (!StringUtils.isEmpty(pageOrdersBO.getName())) {
            baseSql += " and nickname like ?";
            params.add("%" + pageOrdersBO.getName() + "%");
        }
        if (pageOrdersBO.getState() != -1) {
            baseSql += " and stateId = ?";
            params.add(pageOrdersBO.getState());
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("sql", baseSql);
        map.put("params", params);
        return map;
    }

    @Override
    public List<PageOrdersVO> getOrdersByPage(PageOrdersBO pageOrdersBO) {
        Map map = getDynamicSqlAndParams(pageOrdersBO);
        String suffix = (String)map.get("sql");
        List params = (List) map.get("params");
        String sql = "select * from orders " + suffix + " limit ? offset ?";
        params.add(pageOrdersBO.getPagesize());
        params.add((pageOrdersBO.getCurrentPage() - 1) * pageOrdersBO.getPagesize());
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<PageOrdersVO> ordersVOS = null;
        try {
             ordersVOS = runner.query(sql, new BeanListHandler<PageOrdersVO>(PageOrdersVO.class), params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ordersVOS;
    }

    @Override
    public int changeOrder(ChangeOrderBO changeOrderBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Orders former_order = null;
        //取出原来的订单信息
        try {
            former_order = runner.query("select * from orders where id = ?",
                    new BeanHandler<Orders>(Orders.class), changeOrderBO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        //不可状态回退+不可更改数量
        if (former_order.getStateId() > changeOrderBO.getState() || former_order.getGoodsNum() != changeOrderBO.getNum()) {
            return 404;
        }
        try {
            runner.update("update orders set stateId = ? where id = ?", changeOrderBO.getState(), Integer.parseInt(changeOrderBO.getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }

    @Override
    public Orders getOrderInfo(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Orders order = null;
        try {
            order = runner.query("select * from orders where id = ?", new BeanHandler<Orders>(Orders.class), Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Spec> getOrderSpecs(Integer goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Spec> specs = null;
        try {
            specs = runner.query("select * from spec where goodsId = ?", new BeanListHandler<Spec>(Spec.class), goodsId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return specs;
    }

    @Override
    public Id getCurSpecId(Integer goodsId, String spec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer curSpecId = null;
        try {
            curSpecId = (Integer)runner.query("select id from spec where goodsId = ? and specName = ?",
                    new ScalarHandler(), goodsId, spec);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Id(curSpecId.intValue());
    }

    @Override
    public void deleteOrder(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from orders where id = ?", Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
