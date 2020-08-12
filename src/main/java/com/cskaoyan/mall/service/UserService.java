package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.model.bo.PwdBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    List<User> allUser();

    List<User> searchUsers(String word);

    int deleteUser(String id);

    int login(AdminLoginBO loginBO);

    void signUp(User user);

    User data(String nickname);

    int updatePwd(PwdBO pwdBO);

    void updateUserData(User user);
}
