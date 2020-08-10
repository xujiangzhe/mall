package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.OrdersDao;
import com.cskaoyan.mall.dao.OrdersDaoImpl;
import com.cskaoyan.mall.model.Id;
import com.cskaoyan.mall.model.Orders;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.OrderVO;
import com.cskaoyan.mall.model.vo.PageOrdersVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrdersService {

    private OrdersDao ordersDao = new OrdersDaoImpl();

    public static List<Type> generateStateMap() {
        List<Type> sl = new ArrayList<>();
        sl.add(new Type(0, "未付款"));
        sl.add(new Type(1, "未发货"));
        sl.add(new Type(2, "已发货"));
        sl.add(new Type(3, "已完成订单"));
        return sl;
    }

    @Override
    public Map<String, Object> ordersByPage(PageOrdersBO pageOrdersBO) {
        Integer count = ordersDao.getTotalCount(pageOrdersBO);
        List<PageOrdersVO> pageOrdersVOList = ordersDao.getOrdersByPage(pageOrdersBO);
        Map map = new HashMap();
        map.put("total", count);
        map.put("orders",pageOrdersVOList);
        return map;
    }

    @Override
    public int changeOrder(ChangeOrderBO changeOrderBO) {
        return ordersDao.changeOrder(changeOrderBO);
    }

    @Override
    public OrderVO order(String id) {
        Orders om = ordersDao.getOrderInfo(id);
        List<Spec> specs = ordersDao.getOrderSpecs(om.getGoodsId());
        List<Type> states = generateStateMap();
        Id curState = new Id(om.getStateId());
        Id curSpec = ordersDao.getCurSpecId(om.getGoodsId(), om.getSpec());
        return new OrderVO(om.getId(), (int)om.getAmount(), om.getGoodsNum(), om.getGoodsDetailId(),
                om.getStateId(), om.getGoods(), specs, states, curState, curSpec);
    }

    @Override
    public void deleteOrder(String id) {
        ordersDao.deleteOrder(id);
    }
}
