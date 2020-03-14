package com.jimmy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.dao.UserDao;
import com.jimmy.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception{
        if (!redisTemplate.hasKey("user")){
            List<User> users = userDao.findAll();
            ObjectMapper objectMapper=new ObjectMapper();
            String usersJson = objectMapper.writeValueAsString(users);
            redisTemplate.opsForValue().set("user",usersJson);
            System.out.println("--------------数据库获得数据----------------");
        }else {
            System.out.println("--------------redis中获取数据---------------");
            System.out.println(redisTemplate.opsForValue().get("user"));
        }
    }
}
