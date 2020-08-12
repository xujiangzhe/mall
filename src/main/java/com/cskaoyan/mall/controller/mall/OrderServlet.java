package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AddOrderBO;
import com.cskaoyan.mall.model.vo.GoodsMsgVO;
import com.cskaoyan.mall.model.vo.OrderGoodsInfoVO;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.GoodsServiceImpl;
import com.cskaoyan.mall.service.OrderServiceImpl;
import com.cskaoyan.mall.service.OrdersService;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {

    private OrdersService ordersService = new OrderServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
        if("addOrder".equalsIgnoreCase(action)){
            addOrder(request, response);
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AddOrderBO addOrderBO = gson.fromJson(requestBody, AddOrderBO.class);
        ordersService.addOrder(addOrderBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
        if (action.contains("getOrderByState")){
            getOrderByState(request, response);
        }
        else if (action.contains("deleteOrder")) {
            deleteOrder(request, response);
        }
        else if (action.contains("pay")) {
            pay(request, response);
        }
    }


    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        ordersService.pay(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        ordersService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) {
        String state = request.getParameter("state");
        String nickname = request.getParameter("token");
        List<OrderGoodsInfoVO> orderGoodsInfoVOS = ordersService.getOrderByState(state, nickname);
        try {
            response.getWriter().println(gson.toJson(Result.ok(orderGoodsInfoVOS)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
