package Servlet;

import Class.User_Problem.User_ProblemDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

@WebServlet(name = "AcceptProblemsServlet",urlPatterns = "/accept")
public class AcceptProblemsServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class AcceptProblems{
        public ArrayList<String> getProblems_id() {
            return problems_id;
        }
        public void setProblems_id(ArrayList<String> problems_id) {
            this.problems_id = problems_id;
        }
        private ArrayList<String> problems_id;

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String responseJson=new String();  //返回的结果

        User_ProblemDao user_problemDao=new User_ProblemDao();
        AcceptProblems acceptProblems=new AcceptProblems();
        HashMap<String,ArrayList<String>> problems=new HashMap<String,ArrayList<String>>();

        String username=new String();
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("username")){
                    username=cookie.getValue();
                }
            }
        }

        try {
//            Collections.sort(problems);
            problems=user_problemDao.selectOne(username);
            ArrayList<String> sort=new ArrayList<String>();
            sort=problems.get(username);
            Collections.sort(sort);
            acceptProblems.setProblems_id(sort);

        } catch (Exception e) {
            e.printStackTrace();
        }
        responseJson=gson.toJson(acceptProblems);
        response.getWriter().write(responseJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
