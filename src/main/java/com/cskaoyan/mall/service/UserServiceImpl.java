package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.AdminDaoImpl;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.dao.UserDaoImpl;
import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.model.bo.PwdBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }

    @Override
    public List<User> searchUsers(String word) {
        return userDao.searchUsers(word);
    }

    @Override
    public int deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int login(AdminLoginBO loginBO) {
        return userDao.login(loginBO);
    }

    @Override
    public void signUp(User user) {
        userDao.signUp(user);
    }

    @Override
    public User data(String nickname) {
        return userDao.data(nickname);
    }

    @Override
    public int updatePwd(PwdBO pwdBO) {
        return userDao.updatePwd(pwdBO);
    }

    @Override
    public void updateUserData(User user) {
        userDao.updateUserData(user);
    }

}
