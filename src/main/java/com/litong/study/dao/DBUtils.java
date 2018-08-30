package com.litong.study.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.*;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Service;

@Service
public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = DBUtils.class;

    //文档地址：https://blog.csdn.net/u012702547/article/details/54408761
    public static SqlSessionFactory initSqlSessionFactory() {
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                PooledDataSource dataSource = new PooledDataSource();
                dataSource.setDriver("com.mysql.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost:3306/litong?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
                dataSource.setUsername("root");
                dataSource.setPassword("123456");
                TransactionFactory transactionFactory = new JdbcTransactionFactory();
                Environment environment = new Environment("development", transactionFactory, dataSource);
                Configuration configuration = new Configuration(environment);
                configuration.addMapper(UserMapper.class);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSessionFactory initSqlSessionFactoryByXml() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if(sqlSessionFactory==null) {
            initSqlSessionFactoryByXml();
        }
        return sqlSessionFactory.openSession();
    }

    public static SqlSessionFactory initSqlSessionFactory2() {
        InputStream cfgStream = null;
        Reader cfgReader = null;
        InputStream proStream = null;
        Reader proReader = null;
        Properties properties = null;
        try {
            cfgStream = Resources.getResourceAsStream("mybatis-conf.xml");
            cfgReader = new InputStreamReader(cfgStream);
            proStream = Resources.getResourceAsStream("db.properties");
            proReader = new InputStreamReader(proStream);
            properties = new Properties();
            properties.load(proReader);
            //获取密文用户名和密码
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            //解密
            properties.setProperty("username", ((String) username).substring(0, username.indexOf("-")));
            properties.setProperty("password", ((String) password).substring(0, password.indexOf("-")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader,properties);
            }
        }
        return sqlSessionFactory;
    }
}
