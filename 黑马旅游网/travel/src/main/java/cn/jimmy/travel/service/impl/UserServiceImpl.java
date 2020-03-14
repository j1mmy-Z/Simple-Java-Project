package cn.jimmy.travel.service.impl;

import cn.jimmy.travel.dao.UserDao;
import cn.jimmy.travel.dao.impl.UserDaoImpl;
import cn.jimmy.travel.domain.User;
import cn.jimmy.travel.service.UserService;
import cn.jimmy.travel.util.MailUtils;
import cn.jimmy.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        User u = dao.findByUsername(user.getUsername());
        if (u != null) {
            return false;
        } else  {
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            dao.SaveUser(user);

            String content = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");

            return true;
        }
    }

    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if (user!=null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
