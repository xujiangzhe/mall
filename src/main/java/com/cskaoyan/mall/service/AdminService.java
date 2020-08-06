package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.bo.AdminLoginBO;

import java.util.List;

public interface AdminService {
    int login(AdminLoginBO loginBO);

    List<Admin> allAdmins();
}
