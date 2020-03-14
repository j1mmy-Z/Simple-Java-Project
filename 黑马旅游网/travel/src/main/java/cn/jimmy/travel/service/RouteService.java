package cn.jimmy.travel.service;

import cn.jimmy.travel.domain.PageBean;
import cn.jimmy.travel.domain.Route;

public interface RouteService {
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    public Route findOne(String rid);


}
