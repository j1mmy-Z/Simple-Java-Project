package com.jimmy.dao;

import com.jimmy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Select("select * from sys_user")
    public List<User> findAll();
}
