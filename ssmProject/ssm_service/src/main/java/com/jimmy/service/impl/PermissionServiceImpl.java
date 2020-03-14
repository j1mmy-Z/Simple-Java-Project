package com.jimmy.service.impl;

import com.jimmy.dao.PermissionDao;
import com.jimmy.domain.Permission;
import com.jimmy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao ;

    @Override
    public List<Permission> findAll() throws Exception {
        return  permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
