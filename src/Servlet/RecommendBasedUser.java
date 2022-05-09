package Servlet;
import Class.Problem.Problem;
import Class.Problem.ProblemDao;
import Recommender.result.RecommendBasedUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="RecommendBasedUser",urlPatterns = "/RecommendBasedUser")
public class RecommendBasedUser extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        try {
            RecommendBasedUsers recommendBasedUsers = new RecommendBasedUsers();
            String[] reResult = new String[50];
            String username = new String();
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        username = cookie.getValue();
                    }
                }
            }
            //System.out.println(username);

            reResult = recommendBasedUsers.recommendResult(username);

            List<Problem> reProblems = new ArrayList<Problem>();

            ProblemDao problemDao = new ProblemDao();

            for (int i = 0; i < 5; i++) {
                //System.out.println(reResult[i]);

                reProblems.add(problemDao.selectOne(Integer.valueOf(reResult[i])));

              //  System.out.println(problemDao.selectOne(Integer.valueOf(reResult[i])).getTest_case_id());
            }

            String recommendProblemJson = gson.toJson(reProblems);

            response.getWriter().write(recommendProblemJson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
