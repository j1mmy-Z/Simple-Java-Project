package com.jimmy.service;

import com.jimmy.domain.Permission;
import com.jimmy.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll() throws Exception;

    public void save(Role role) throws Exception;

    public Role findById(String roleId) throws Exception;

    public List<Permission> findOtherPermissions(String roleId) throws Exception;

    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
