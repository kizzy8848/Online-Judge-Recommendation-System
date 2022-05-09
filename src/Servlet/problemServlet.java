package Servlet;

/*
 *Author : HNU XieZhengYu
 * Email : kizzy8452@gmail.com
 *
 *                              _ooOoo_
 *                             o8888888o
 *                             88" . "88
 *                             (| -_- |)
 *                             O\  =  /O
 *                          ____/`---'\____
 *                        .'  \\|     |//  `.
 *                       /  \\|||  :  |||//  \
 *                      /  _||||| -:- |||||-  \
 *                      |   | \\\  -  /// |   |
 *                      | \_|  ''\---/''  |   |
 *                      \  .-\__  `-`  ___/-. /
 *                    ___`. .'  /--.--\  `. . __
 *                 ."" '<  `.___\_<|>_/___.'  >'"".
 *                | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                \  \ `-.   \_ __\ /__ _/   .-` /  /
 *           ======`-.____`-.___\_____/___.-`____.-'======
 *                              `=---='
 *
 *                          HERE BE BUDDHA
 *                          佛祖保佑 永无bug
 *
 */

import Class.Problem.Problem;
import Class.Problem.ProblemDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "problemServlet",urlPatterns = "/problem")
public class problemServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            //没有id这个参数， 执行查找全部
            try {
                selectAll(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            selectOne(Integer.parseInt(id), response);
        }
    }



    private void selectOne(int problemId, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        ProblemDao problemDAO = new ProblemDao();
        Problem problem = problemDAO.selectOne(problemId);
        String json = gson.toJson(problem);
        resp.getWriter().write(json);
    }

    private void selectAll(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json; charset=utf-8");
        String username=new String();

        Cookie[]  cookie = request.getCookies();
        for(Cookie  c:cookie){
            if(c.getName().equals("username")){
                username=c.getValue();
                HttpSession session1=request.getSession();
                String Id=session1.getId();
                Object user=session1.getAttribute("username");
                Cookie  cookie1 = new  Cookie("JSESSIONID",Id);
                cookie1.setPath("/");
                cookie1.setMaxAge(60*60);
                resp.addCookie(cookie1);
            }
        }
        ProblemDao problemDao = new ProblemDao();

        List<Problem> problems = problemDao.selectAll(username);

        String jsonString = gson.toJson(problems);

        System.out.println(jsonString);

        resp.getWriter().write(jsonString);

    }
}
