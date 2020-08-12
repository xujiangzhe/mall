package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Id;
import com.cskaoyan.mall.model.Orders;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AddOrderBO;
import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.OrderGoodsInfoVO;
import com.cskaoyan.mall.model.vo.PageOrdersVO;

import java.util.List;
import java.util.Map;

public interface OrdersDao {
    Integer getTotalCount(PageOrdersBO pageOrdersBO);

    List<PageOrdersVO> getOrdersByPage(PageOrdersBO pageOrdersBO);

    int changeOrder(ChangeOrderBO changeOrderBO);

    Orders getOrderInfo(String id);

    List<Spec> getOrderSpecs(Integer goodsId);

    Id getCurSpecId(Integer goodsId, String spec);

    void deleteOrder(String id);

    List<OrderGoodsInfoVO> getOrderByState(String state, String nickname);

    void pay(String id);

    void addOrder(AddOrderBO addOrderBO);
}
