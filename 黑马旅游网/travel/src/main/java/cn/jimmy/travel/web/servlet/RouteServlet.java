package cn.jimmy.travel.web.servlet;

import cn.jimmy.travel.domain.PageBean;
import cn.jimmy.travel.domain.Route;
import cn.jimmy.travel.domain.User;
import cn.jimmy.travel.service.FavoriteService;
import cn.jimmy.travel.service.RouteService;
import cn.jimmy.travel.service.impl.FavoriteServiceImpl;
import cn.jimmy.travel.service.impl.RouteServiceImpl;
import org.springframework.dao.support.DaoSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.RMIClassLoader;
import java.sql.RowId;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private RouteService service= new RouteServiceImpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid=0;
        int currentPage=0;
        int pageSize=0 ;
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }


        PageBean<Route> pageBean = service.pageQuery(cid, currentPage, pageSize,rname);
        System.out.println(pageBean);
        writeValue(pageBean,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        Route route = service.findOne(rid);
        writeValue(route,response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user==null){
            uid=0;
        }else {
            uid=user.getUid();
        }

        boolean flag= favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user==null){
            return;
        }else {
            uid=user.getUid();
        }

        favoriteService.add(rid,uid);
    }
}
