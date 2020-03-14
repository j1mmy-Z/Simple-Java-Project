package cn.jimmy.travel.dao;

import cn.jimmy.travel.domain.Route;
import cn.jimmy.travel.domain.Seller;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid,String rname);
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);
    public Route findByRid(int rid);

}
