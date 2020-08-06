package com.cskaoyan.mall.service;

import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.AdminDaoImpl;
import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.*;

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

    @Override
    public int add(AdminAddBO addBO) {
        return adminDao.add(addBO);
    }

    @Override
    public int delete(String id) {
        return adminDao.delete(id);
    }

    @Override
    public Admin getInfo(String id) {
        return adminDao.getInfo(id);
    }

    @Override
    public int update(AdminUpdateBO updateBO) {
        return adminDao.update(updateBO);
    }

    @Override
    public List<Admin> searchAdmins(SearchAdminBO searchAdminBO) {
        return adminDao.searchAdmins(searchAdminBO);
    }

    @Override
    public int changePwd(AdminPwdBO adminPwdBO) {
        return adminDao.changePwd(adminPwdBO);
    }

}
