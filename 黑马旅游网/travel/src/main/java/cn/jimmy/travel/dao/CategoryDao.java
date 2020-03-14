package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
}
