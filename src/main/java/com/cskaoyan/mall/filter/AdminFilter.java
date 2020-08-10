package com.cskaoyan.mall.filter;

import com.cskaoyan.mall.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");
        if(!request.getMethod().equalsIgnoreCase("OPTIONS")){
            //不对options请求做任何判断，因为options请求不会携带cookie
            if(auth(request)){
                //需要验证权限
                String username = (String) request.getSession().getAttribute("username");
                if(username == null){
                    response.getWriter().println(new Gson().toJson(Result.error("当前接口需要登录才可以访问")));
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean auth(HttpServletRequest request) {
        if("/api/admin/admin/login".equalsIgnoreCase(request.getRequestURI()) || "/api/admin/admin/logoutAdmin".equalsIgnoreCase(request.getRequestURI())){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
