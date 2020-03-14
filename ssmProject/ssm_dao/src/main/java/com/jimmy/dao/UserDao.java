package com.jimmy.dao;

import com.jimmy.domain.Role;
import com.jimmy.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = List.class,many = @Many(select = "com.jimmy.dao.RoleDao.findByUserId"))

    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id =#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.jimmy.dao.RoleDao.findByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    public List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
