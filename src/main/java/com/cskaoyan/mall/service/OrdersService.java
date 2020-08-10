package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.OrderVO;

import java.util.Map;

public interface OrdersService {
    Map<String, Object> ordersByPage(PageOrdersBO pageOrdersBO);

    int changeOrder(ChangeOrderBO changeOrderBO);

    OrderVO order(String id);

    void deleteOrder(String id);
}
