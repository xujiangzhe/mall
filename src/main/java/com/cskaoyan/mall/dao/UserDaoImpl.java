package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.model.Goods;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.model.bo.AdminLoginBO;
import com.cskaoyan.mall.model.bo.PwdBO;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    @Override
    public int login(AdminLoginBO loginBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from user where email = ? and pwd = ?",
                    new ScalarHandler(), loginBO.getEmail(), loginBO.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        return query != 0 ? 200:404;
    }

    @Override
    public void signUp(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into user (id, email, nickname, pwd, recipient, address, phone) values (null, ?, ?, ?, ?, ?, ?)",
                    user.getEmail(), user.getNickname(), user.getPwd(), user.getRecipient(), user.getAddress(), user.getPhone());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User data(String nickname) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where nickname = ?", new BeanHandler<User>(User.class), nickname);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public int updatePwd(PwdBO pwdBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where id = ?", new BeanHandler<User>(User.class), Integer.parseInt(pwdBO.getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (!pwdBO.getOldPwd().equals(user.getPwd())) {
            return 404;
        }
        try {
            runner.update("update user set pwd = ? where id = ?", pwdBO.getNewPwd(), Integer.parseInt(pwdBO.getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 200;
    }

    @Override
    public void updateUserData(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update user set nickname = ?, recipient = ?, address = ?, phone = ? where id = ?",
                    user.getNickname(), user.getRecipient(), user.getAddress(), user.getPhone(), user.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
