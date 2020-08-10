package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.Spec;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.model.bo.*;
import com.cskaoyan.mall.model.vo.GoodsInfoVO;
import com.cskaoyan.mall.model.vo.GoodsUpdateVO;
import com.cskaoyan.mall.model.vo.NoReplyMsgVO;
import com.cskaoyan.mall.model.vo.RepliedMsgVO;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.GoodsServiceImpl;
import com.cskaoyan.mall.utils.FileUploadUtils;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("imgUpload".equals(action)) {
            imgUpload(request,response);
        }
        else if ("addGoods".equals(action)){
            addGoods(request,response);
        }
        else if ("addType".equals(action)) {
            addType(request, response);
        }
        else if ("addSpec".equals(action)) {
            addSpec(request, response);
        }
        else if ("deleteSpec".equals(action)) {
            deleteSpec(request, response);
        }
        else if ("updateGoods".equals(action)) {
            updateGoods(request, response);
        }
        else if ("reply".equals(action)) {
            reply(request, response);
        }
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody, ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        goodsService.deleteGoods(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsUpdateVO goodsUpdateVO= gson.fromJson(requestBody, GoodsUpdateVO.class);
        goodsService.updateGoods(goodsUpdateVO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        DeleteSpecBO deleteSpecBO = gson.fromJson(requestBody, DeleteSpecBO.class);
        goodsService.deleteSpec(deleteSpecBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Spec spec = gson.fromJson(requestBody, Spec.class);
        Spec sp = goodsService.addSpec(spec);
        response.getWriter().println(gson.toJson(Result.ok(sp)));
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Type type = gson.fromJson(requestBody, Type.class);
        if ("".equals(type.getName())) {
            response.getWriter().println(Result.error("类别名不能为空"));
            return;
        }
        goodsService.addType(type);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AddGoodsBO addGoodsBO = gson.fromJson(requestBody, AddGoodsBO.class);
        List<AddSpecBO> sList = addGoodsBO.getSpecList();
        for (AddSpecBO s : sList) {
            if (Integer.parseInt(s.getStockNum()) < 0 || Double.parseDouble(s.getUnitPrice()) < 0
            || s.getSpecName().equals("")) {
                response.getWriter().println(gson.toJson(Result.error("规格参数输入错误")));
                return;
            }
        }
        goodsService.addGoods(addGoodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void imgUpload(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file = (String)map.get("file");
        String domain = (String) getServletContext().getAttribute("domain");
        try {
            response.getWriter().println(gson.toJson(Result.ok(domain + "/" + file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if ("getType".equals(action)) {
            getType(request, response);
        }
        else if ("getGoodsByType".equals(action)) {
            getGoodsByType(request,response);
        }
        else if (action.contains("getGoodsInfo")) {
            getGoodsInfo(request,response);
        }
        else if (action.contains("deleteGoods")) {
            deleteGoods(request, response);
        }
        else if ("noReplyMsg".equals(action)) {
            noReplyMsg(request, response);
        }
        else if ("repliedMsg".equals(action)) {
            repliedMsg(request, response);
        }
    }

    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<RepliedMsgVO> repliedMsgVO = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(repliedMsgVO)));
    }

    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<NoReplyMsgVO> noReplyMsgVO = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok(noReplyMsgVO)));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        GoodsInfoVO goodsInfoVO = goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsInfoVO)));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<Goods> goodsList = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsList)));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
