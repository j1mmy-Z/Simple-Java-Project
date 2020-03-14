package cn.jimmy.travel.web.servlet;

import cn.jimmy.travel.domain.ResultInfo;
import cn.jimmy.travel.domain.User;
import cn.jimmy.travel.service.UserService;
import cn.jimmy.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    //注册
    public void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//防止验证码复用
        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(checkCode)){

            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
            writeValue(resultInfo,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user =new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = service.register(user);

        ResultInfo resultInfo = new ResultInfo();

        if (flag){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败！");
        }

        writeValue(resultInfo,response);
    }
    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//防止验证码复用
        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(checkCode)){

            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
            writeValue(resultInfo,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User login_user = new User();
        try {
            BeanUtils.populate(login_user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User user = service.login(login_user);

        ResultInfo resultInfo = new ResultInfo();
        if(user==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误！");
        }
        if (user!=null&&!"Y".equals(user.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户尚未激活，请登录邮箱激活！");
        }
        if(user!=null&&"Y".equals(user.getStatus())){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("user",user);
        }

        writeValue(resultInfo,response);
    }
    //查找一个用户
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        writeValue(user,response);
    }
    //退出
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    //激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String code = request.getParameter("code");
        if (code!=null){
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
