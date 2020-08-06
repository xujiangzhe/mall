package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.AdminDaoImpl;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.dao.UserDaoImpl;
import com.cskaoyan.mall.model.User;

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

}
