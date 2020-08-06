package com.cskaoyan.mall.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {

    private static DataSource dataSource;

    static {
        //从ds里面去获取，得到datasource即可
        try {
            Properties properties = new Properties();
            //流 -----> file ----- path
            //假如想将配置文件放在WEB-INF目录下 realPath
            //那么当前配置文件必须要有一个API来接收request或者context，否则无法获取路径
            //但是如果这么做了以后，那么今后该配置文件只能处理EE项目，SE项目需要重新在写一份
            //那么，有没有一种方式，既可以处理EE也可以处理SE呢？
            //有办法。利用类加载器来帮助我们获取流信息
            //输入空字符串的时候，其实是到com同级目录
            InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }


    public static DataSource getDataSource(){
        return dataSource;
    }
}
