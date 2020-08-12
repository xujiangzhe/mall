package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AddOrderBO;
import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.OrderGoodsInfoVO;
import com.cskaoyan.mall.model.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface OrdersService {
    Map<String, Object> ordersByPage(PageOrdersBO pageOrdersBO);

    int changeOrder(ChangeOrderBO changeOrderBO);

    OrderVO order(String id);

    void deleteOrder(String id);

    List<OrderGoodsInfoVO> getOrderByState(String state, String nickname);

    void pay(String id);

    void addOrder(AddOrderBO addOrderBO);
}
