package com.litong.study.test;

import com.litong.study.dao.DBUtils;
import com.litong.study.dao.UserMapper;
import com.litong.study.model.User;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void testUserByOld1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(3l);
            System.out.println(user.toString());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testUserByOld2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            User user = (User)sqlSession.selectOne("com.litong.study.dao.UserMapper.getUser",1l);
            System.out.println(user.toString());
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
             if (sqlSession != null) {
                 sqlSession.close();
             }
        }
    }

    @Test
    public void testUserByXml() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(6l);
            System.out.println(user.toString());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession !=null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void createUser() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for (int i=5 ; i<=7; i++) {
                userMapper.insertUser(new User(null,"u-" +i,"password-"+i,"address is:"+i));
            }
            //User user = new User(null,"u","p","a");

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
