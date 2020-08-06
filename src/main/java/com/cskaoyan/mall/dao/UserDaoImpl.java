package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> allUser() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> users = null;
        try {
            users = runner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchUsers(String word) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> users = null;
        if (word.equals("")) {
            try {
                users = runner.query("select * from user", new BeanListHandler<User>(User.class));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            try {
                users = runner.query("select * from user where nickname like ?", new BeanListHandler<User>(User.class),
                        "%" + word + "%");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public int deleteUser(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from user where id = ?", Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 500;
        }
        return 200;
    }
}
