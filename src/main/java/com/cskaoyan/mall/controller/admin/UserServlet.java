package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.AdminServiceImpl;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if ("allUser".equals(action)) {
            allUser(request, response);
        }
        else if (action.contains("searchUser")) {
            searchUser(request, response);
        }
        else if (action.contains("deleteUser")) {
            deleteUser(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int code = userService.deleteUser(id);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("删除失败")));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(userList)));
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String word = request.getParameter("word");
        List<User> users = userService.searchUsers(word);
        response.getWriter().println(gson.toJson(Result.ok(users)));
    }
}
