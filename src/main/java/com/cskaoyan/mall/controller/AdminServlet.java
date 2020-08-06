package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.model.vo.AdminLoginVO;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.AdminServiceImpl;
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

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private Gson gson = new Gson();

    private AdminService adminService = new AdminServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dopost");
        ///api/admin/admin/login
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("login".equals(action)){
            login(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String requestBody = HttpUtils.getRequestBody(request);
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);
        //判断是否为空 StringUtils
        if(StringUtils.isEmpty(loginBO.getEmail()) || StringUtils.isEmpty(loginBO.getPwd())){
            //返回某个响应结果
            response.getWriter().println(gson.toJson(Result.error("参数不能为空")));
            return;
        }
       int code =  adminService.login(loginBO);
        //返回响应结果
        if(code == 200){
            response.getWriter().println(gson.toJson(Result.ok(new AdminLoginVO(loginBO.getEmail(),loginBO.getEmail()))));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("确认用户名和密码")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("allAdmins".equals(action)){
            allAdmins(request, response);
        }
    }

    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        response.getWriter().println(gson.toJson(Result.ok(adminList)));
    }
}
