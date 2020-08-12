package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.bo.AskGoodsMsgBO;
import com.cskaoyan.mall.model.vo.CommentGoods2VO;
import com.cskaoyan.mall.model.vo.CommentGoodsVO;
import com.cskaoyan.mall.model.vo.GoodsInfo2VO;
import com.cskaoyan.mall.model.vo.GoodsMsgVO;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.GoodsServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");
        if ("askGoodsMsg".equals(action)) {
            askGoodsMsg(request, response);
        }
    }

    private void askGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AskGoodsMsgBO askGoodsMsgBO = gson.fromJson(requestBody, AskGoodsMsgBO.class);
        //User user = gson.fromJson(requestBody, User.class);
        goodsService.askGoodsMsg(askGoodsMsgBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");
        if (action.contains("getGoodsByType")){
            getGoodsByType(request, response);
        }
        else if (action.contains("searchGoods")){
            searchGoods(request, response);
        }
        else if (action.contains("getGoodsInfo")) {
            getGoodsInfo(request, response);
        }
        else if (action.contains("getGoodsMsg")) {
            getGoodsMsg(request, response);
        }
        else if (action.contains("getGoodsComment")) {
            getGoodsComment(request, response);
        }
    }

    private void getGoodsComment(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("goodsId");
        CommentGoodsVO commentGoodsVO = goodsService.getGoodsComment(id);
        if (commentGoodsVO.getCommntList().size() != 0) {
            try {
                response.getWriter().println(gson.toJson(Result.ok(commentGoodsVO)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                response.getWriter().println(gson.toJson(Result.ok(new CommentGoods2VO("NaN"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        List<GoodsMsgVO> goodsMsgVO = goodsService.getGoodsMsg(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsMsgVO)));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        GoodsInfo2VO goodsInfo2VO = goodsService.getGoodsInfo2(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsInfo2VO)));
    }

    private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        List<Goods> goodsList = goodsService.searchGoods(keyword);
        response.getWriter().println(gson.toJson(Result.ok(goodsList)));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("typeId");
        List<Goods> goodsList = goodsService.getGoodsByType(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsList)));
    }
}
