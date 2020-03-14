package cn.jimmy.travel.web.servlet_desert;

import cn.jimmy.travel.service.UserService;
import cn.jimmy.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code!=null){
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            System.out.println(flag);
            String msg=null;
            if(flag){
                msg="激活成功，请<a href='login.html'>登录</a>!";
            }else {
                msg="激活失败！别乱来!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
