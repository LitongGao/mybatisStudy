package com.litong.study.test;

import com.litong.study.dao.DBUtils;
import com.litong.study.dao.UserMapper;
import com.litong.study.model.User;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void testUser1() {
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
    public void testUser2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();

        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
             if (sqlSession != null) {
                 sqlSession.close();
             }
        }
    }
}
