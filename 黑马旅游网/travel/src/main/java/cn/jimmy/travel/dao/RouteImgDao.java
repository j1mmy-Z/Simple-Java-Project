package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    public List<RouteImg> findByRid(int rid);
}
