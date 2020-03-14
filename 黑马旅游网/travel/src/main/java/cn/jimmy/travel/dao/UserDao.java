package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.User;

public interface UserDao {
    public User findByUsername(String username);
    public void SaveUser(User user);
    public User findByCode(String code);
    public void updateStatus(User user);
    public User findByUsernameAndPassword(String username, String password);
}
