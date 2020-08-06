package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.*;
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
        else if ("addAdminss".equals(action)) {
            addAdmins(request, response);
        }
        else if ("updateAdminss".equals(action)) {
            updateAdmins(request, response);
        }
        else if ("getSearchAdmins".equals(action)) {
            getSearchAdmins(request, response);
        }
        else if ("changePwd".contains(action)) {
            changeAdminPwd(request, response);
        }
    }

    private void changeAdminPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminPwdBO adminPwdBO = gson.fromJson(requestBody, AdminPwdBO.class);
        if (StringUtils.isEmpty(adminPwdBO.getOldPwd()) || StringUtils.isEmpty(adminPwdBO.getNewPwd())
                || StringUtils.isEmpty(adminPwdBO.getConfirmPwd())) {
            response.getWriter().println(gson.toJson(Result.error("参数不能为空")));
            return;
        }
        if (!adminPwdBO.getNewPwd().equals(adminPwdBO.getConfirmPwd())) {
            response.getWriter().println(gson.toJson(Result.error("新密码与确认新密码不同")));
            return;
        }
        int code = adminService.changePwd(adminPwdBO);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("修改失败")));
    }

    private void updateAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminUpdateBO updateBO = gson.fromJson(requestBody, AdminUpdateBO.class);
        int code = adminService.update(updateBO);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("更新失败")));
    }

    private void addAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminAddBO addBO = gson.fromJson(requestBody, AdminAddBO.class);
        int code = adminService.add(addBO);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("该账号不允许重复使用")));
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        SearchAdminBO searchAdminBO = gson.fromJson(requestBody, SearchAdminBO.class);
        if (StringUtils.isEmpty(searchAdminBO.getEmail()) && StringUtils.isEmpty(searchAdminBO.getNickname())) {
            response.getWriter().println(gson.toJson(Result.error("输入内容不能为空")));
            return;
        }
        List<Admin> admins = adminService.searchAdmins(searchAdminBO);
        response.getWriter().println(gson.toJson(Result.ok(admins)));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        else if (action.contains("deleteAdmins")) {
            deleteAdmins(request, response);
        }
        else if (action.contains("getAdminsInfo")) {
            getAdminsInfo(request, response);
        }
    }

    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Admin admin = adminService.getInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(admin)));
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int code = adminService.delete(id);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("删除失败")));
    }

    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        response.getWriter().println(gson.toJson(Result.ok(adminList)));
    }
}
