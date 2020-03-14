package com.jimmy.controller;

import com.jimmy.dao.RoleDao;
import com.jimmy.domain.Permission;
import com.jimmy.domain.Role;
import com.jimmy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }


    @RequestMapping("/save.do")
    public String add(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception {
        Role role = roleService.findById(roleId);
        List<Permission> otherPermissions=roleService.findOtherPermissions(roleId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",otherPermissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,
                                      @RequestParam(name = "ids" ,required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
