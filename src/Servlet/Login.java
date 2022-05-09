package Servlet;



import Class.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends HttpServlet {
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String encode = request.getServletContext().getInitParameter("encode");
        //设置请求和响应的编码
        request.setCharacterEncoding(encode);
        //resp.setCharacterEncoding();
        response.setContentType("text/html;charset="+encode);
        try {
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
            //下边就是加载数据库的过程
            int flag=userDao.verification(name,pwd);
            if (flag==1){
                //5.跳转到
                response.sendRedirect("oj.jsp");
            }else if(flag==0){
                response.getWriter().write("对不起你帐号名有误，请返回<a href='index.jsp'>登录</a>或<a href='register.jsp'>注册</a>");
            }else{
                response.getWriter().write("你输入的用户不存在，请返回<a href='index.jsp'>登录</a>或<a href='register.jsp'>注册</a>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}