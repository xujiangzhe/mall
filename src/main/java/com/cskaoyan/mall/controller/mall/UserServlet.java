package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.model.bo.PwdBO;
import com.cskaoyan.mall.model.vo.AdminLoginVO;
import com.cskaoyan.mall.model.vo.UserSignUpVO;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.UserServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");
        if("login".equalsIgnoreCase(action)){
            login(request, response);
        }
        if("signup".equalsIgnoreCase(action)) {
            signUp(request, response);
        }
        if ("updatePwd".equalsIgnoreCase(action)) {
            updatePwd(request, response);
        }
        if ("updateUserData".equalsIgnoreCase(action)) {
            updateUserData(request, response);
        }
    }

    private void updateUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        User user = gson.fromJson(requestBody, User.class);
        userService.updateUserData(user);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        PwdBO pwdBO = gson.fromJson(requestBody, PwdBO.class);
        int code = userService.updatePwd(pwdBO);
        if (code == 200) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
        else
            response.getWriter().println(gson.toJson(Result.error("旧密码不正确！")));
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        User user = gson.fromJson(requestBody, User.class);
        userService.signUp(user);
        response.getWriter().println(gson.toJson(Result.ok(new UserSignUpVO(0, user.getNickname(), user.getNickname()))));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);
        if(StringUtils.isEmpty(loginBO.getEmail()) || StringUtils.isEmpty(loginBO.getPwd())){
            //返回某个响应结果
            response.getWriter().println(gson.toJson(Result.error("参数不能为空")));
            return;
        }
        int code =  userService.login(loginBO);
        //返回响应结果
        if(code == 200){
            request.getSession().setAttribute("username", loginBO.getEmail());
            response.getWriter().println(gson.toJson(Result.ok(new AdminLoginVO(loginBO.getEmail(),loginBO.getEmail()))));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("确认用户名和密码")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
        if (action.contains("data")) {
            data(request, response);
        }
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("token");
        User user = userService.data(nickname);
        response.getWriter().println(gson.toJson(Result.ok(user)));
    }
}
