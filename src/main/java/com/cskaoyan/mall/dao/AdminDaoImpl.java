package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
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
}
