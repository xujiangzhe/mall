package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.User;

import java.util.List;

public interface UserService {

    List<User> allUser();

    List<User> searchUsers(String word);

    int deleteUser(String id);
}
