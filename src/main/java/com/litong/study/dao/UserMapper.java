package com.litong.study.dao;

import com.litong.study.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select(value = "select * from User where id = #{id}")
    public User getUser(Long id);

    //@Insert(value = "")



}
