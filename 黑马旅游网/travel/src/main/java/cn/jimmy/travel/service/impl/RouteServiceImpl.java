package cn.jimmy.travel.service.impl;

import cn.jimmy.travel.dao.FavoriteDao;
import cn.jimmy.travel.dao.RouteDao;
import cn.jimmy.travel.dao.RouteImgDao;
import cn.jimmy.travel.dao.SellerDao;
import cn.jimmy.travel.dao.impl.FavoriteDaoImpl;
import cn.jimmy.travel.dao.impl.RouteDaoImpl;
import cn.jimmy.travel.dao.impl.RouteImgDaoImpl;
import cn.jimmy.travel.dao.impl.SellerDaoImpl;
import cn.jimmy.travel.domain.PageBean;
import cn.jimmy.travel.domain.Route;
import cn.jimmy.travel.domain.RouteImg;
import cn.jimmy.travel.domain.Seller;
import cn.jimmy.travel.service.RouteService;
import sun.tools.tree.PreIncExpression;

import java.util.List;


public class RouteServiceImpl implements RouteService {
    private SellerDao sellerDao = new SellerDaoImpl();
    private RouteImgDao imgDao = new RouteImgDaoImpl();
    private RouteDao dao = new RouteDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pageBean = new PageBean<Route>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalCount = dao.findTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);
        int start = (currentPage-1)*pageSize;
        pageBean.setList(dao.findByPage(cid, start,pageSize,rname));
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(String rid) {
        Route route = dao.findByRid(Integer.parseInt(rid));
        List<RouteImg> imgList = imgDao.findByRid(Integer.parseInt(rid));
        route.setRouteImgList(imgList);
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }


}
