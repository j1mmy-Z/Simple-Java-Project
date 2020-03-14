package cn.jimmy.travel.service;

import cn.jimmy.travel.domain.User;

public interface UserService {
    public boolean register(User user);//用户注册

    boolean active(String code);

    public User login(User user);
}
