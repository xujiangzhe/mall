package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.bo.AdminLoginBO;

import java.util.List;

public interface AdminDao {
    int login(AdminLoginBO loginBO);

    List<Admin> allAdmins();
}
