package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.bo.ChangeOrderBO;
import com.cskaoyan.mall.model.bo.PageOrdersBO;
import com.cskaoyan.mall.model.vo.GoodsUpdateVO;
import com.cskaoyan.mall.model.vo.OrderVO;
import com.cskaoyan.mall.service.OrderServiceImpl;
import com.cskaoyan.mall.service.OrdersService;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/order/*")
public class OrdersServlet extends HttpServlet {

    private Gson gson = new Gson();

    private OrdersService ordersService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if ("ordersByPage".equals(action)) {
            ordersBypage(request, response);
        }
        if ("changeOrder".equals(action)) {
            changeOrder(request, response);
        }
    }

    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        ChangeOrderBO changeOrderBO = gson.fromJson(requestBody, ChangeOrderBO.class);
        int code = ordersService.changeOrder(changeOrderBO);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
        else {
            response.getWriter().println(gson.toJson(Result.error("参数有误,更改失败")));
        }
    }

    private void ordersBypage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        PageOrdersBO pageOrdersBO = null;
        try {
            pageOrdersBO = gson.fromJson(requestBody, PageOrdersBO.class);
            if (!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit1())) {
                Double.parseDouble(pageOrdersBO.getMoneyLimit1());
            }
            if (!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit2())) {
                Double.parseDouble(pageOrdersBO.getMoneyLimit2());
            }
        }catch (Exception e) {
            response.getWriter().println(gson.toJson(Result.error("参数类型不匹配")));
            return;
        }
        Map<String, Object> result = ordersService.ordersByPage(pageOrdersBO);
        response.getWriter().println(gson.toJson(Result.ok(result)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if (action.contains("order")) {
            order(request, response);
        }
        else if (action.contains("deleteOrder")) {
            deleteOrder(request, response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        ordersService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        OrderVO orderVO = ordersService.order(id);
        response.getWriter().println(gson.toJson(Result.ok(orderVO)));
    }
}
