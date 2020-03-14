package com.jimmy.controller;

import com.jimmy.dao.UserDao;
import com.jimmy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAllUser(){
        List<User> list = userDao.findAll();
        return list;
    }
}
