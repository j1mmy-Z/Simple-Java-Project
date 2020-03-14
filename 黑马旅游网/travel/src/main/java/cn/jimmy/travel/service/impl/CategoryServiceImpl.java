package cn.jimmy.travel.service.impl;

import cn.jimmy.travel.dao.CategoryDao;
import cn.jimmy.travel.dao.impl.CategoryDaoImpl;
import cn.jimmy.travel.domain.Category;
import cn.jimmy.travel.service.CategoryService;
import cn.jimmy.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.swing.text.html.CSS;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao =new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list =null;
        if (category==null||category.size()==0){
            list = dao.findAll();
            for (int i=0;i<list.size();i++)
            jedis.zadd("category",list.get(i).getCid(),list.get(i).getCname());
        }else {
            list=new ArrayList<Category>();
            for (Tuple tuple : category) {
                Category c= new Category();
                c.setCid((int) tuple.getScore());
                c.setCname(tuple.getElement());
                list.add(c);
            }
        }
        return list;
    }
}
