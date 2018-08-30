package com.litong.study.controller;

import java.util.List;

import com.litong.study.dao.DBUtils;
import com.litong.study.dao.UserMapper;
import com.litong.study.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/litong/")
public class UserController {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;

    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public String getUser() {
        return "The user is " + name + " and the age is "+age +"===" +content;
    }

    @RequestMapping(value = "getUserList",method =RequestMethod.GET)
    public List<User> getUserList() {
        return null;
    }

    @RequestMapping(value = "getUserById",method =RequestMethod.GET)
    public User getUserList(Long id) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.getUser(id);
            System.out.println(user.toString());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession !=null) {
                sqlSession.close();
            }
        }
        return user;
    }
}
