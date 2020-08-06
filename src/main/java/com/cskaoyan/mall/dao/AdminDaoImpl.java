package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.*;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public int login(AdminLoginBO loginBO) {
        //JDBC
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from admin where email = ? and pwd = ?",
                    new ScalarHandler(), loginBO.getEmail(), loginBO.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        return query != 0 ? 200:404;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public int add(AdminAddBO addBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into admin (email, pwd, nickname) values(?, ?, ?)",
                    addBO.getEmail(), addBO.getPwd(), addBO.getNickname());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }

    @Override
    public int delete(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from admin where id = ?", Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }

    @Override
    public Admin getInfo(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?",
                    new BeanHandler<Admin>(Admin.class), Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return admin;
    }

    @Override
    public int update(AdminUpdateBO updateBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update admin set email = ?, nickname = ?, pwd = ? where id = ?",
                    updateBO.getEmail(), updateBO.getPwd(), updateBO.getNickname(), updateBO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }

    @Override
    public List<Admin> searchAdmins(SearchAdminBO searchAdminBO) {
        String baseSql = "select * from admin where 1 = 1";
        List<Object> list = new ArrayList<>();
        if (!StringUtils.isEmpty(searchAdminBO.getEmail())) {
            baseSql += " and email like ?";
            list.add("%" + searchAdminBO.getEmail() + "%");
        }
        if (!StringUtils.isEmpty(searchAdminBO.getNickname())) {
            baseSql += " and nickname like ?";
            list.add("%" + searchAdminBO.getNickname() + "%");
        }
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query(baseSql, new BeanListHandler<Admin>(Admin.class), list.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }

    @Override
    public int changePwd(AdminPwdBO adminPwdBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String trueOldPwd = null;
        try {
            trueOldPwd = (String)runner.query("select pwd from admin where email = ?",
                    new ScalarHandler(), adminPwdBO.getAdminToken());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        if (!trueOldPwd.equals(adminPwdBO.getOldPwd()))
            return 404;
        try {
            runner.update("update admin set pwd = ? where email = ?",
                    adminPwdBO.getNewPwd(), adminPwdBO.getAdminToken());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }


}
