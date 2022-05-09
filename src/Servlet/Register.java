package Servlet;


import Class.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Register",urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String encode = request.getServletContext().getInitParameter("encode");
        //设置请求和响应的编码
        request.setCharacterEncoding(encode);
        //resp.setCharacterEncoding();
        response.setContentType("text/html;charset="+encode);
        try{
            UserDao userDao=new UserDao();
            //从登录页面拿到用户输入的用户名
            String name = request.getParameter("username");
            //从登录页面拿到用户输入的密码
            String pwd = request.getParameter("password");
            //还是在控制台上输出看是否拿到的帐号密码
            Cookie cookie=new Cookie("username",name);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            HttpSession session=request.getSession();
            session.setAttribute("username",name);
            int flag=userDao.register(name,pwd);
            if(flag==0){
                response.getWriter().write("对不起你输入的用户名已存在，请返回重新<a href='register.jsp'>注册</a>");
            }
            else{
                response.sendRedirect("oj.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
