package com.cskaoyan.mall.service;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.*;

import java.util.List;

public interface AdminService {
    int login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    int add(AdminAddBO addBO);

    int delete(String id);

    Admin getInfo(String id);

    int update(AdminUpdateBO updateBO);

    List<Admin> searchAdmins(SearchAdminBO searchAdminBO);

    int changePwd(AdminPwdBO adminPwdBO);

}
