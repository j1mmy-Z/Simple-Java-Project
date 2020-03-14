package com.jimmy.dao;

import com.jimmy.domain.Permission;
import com.jimmy.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.jimmy.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId) throws Exception;

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;
}
