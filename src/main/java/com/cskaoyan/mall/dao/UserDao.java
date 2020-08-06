package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUser();

    List<User> searchUsers(String word);

    int deleteUser(String id);
}
