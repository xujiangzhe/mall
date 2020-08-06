package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.AdminDaoImpl;
import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.bo.AdminLoginBO;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int login(AdminLoginBO loginBO) {

        return adminDao.login(loginBO);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }
}
