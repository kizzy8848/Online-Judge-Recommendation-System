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
import Class.User_Problem.User_ProblemDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Class.Problem.ProblemDao;
import Judger.JudgeSources.*;
import Judger.Judger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet(name = "JudgeServlet",urlPatterns = "/judge")
public class JudgeServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class CompileRequest {
        private int id;
        private String code;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
    }
    static class CompileResponse {
        private int ok;
        private String reason;
        public int getOk() {
            return ok;
        }
        public void setOk(int ok) {
            this.ok = ok;
        }
        public String getReason() {
            return reason;
        }
        public void setReason(String reason) {
            this.reason = reason;
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * -1 :错误
     * 0：对一部分
     * 1：accept
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String username=new String();
        String responseJson=new String();//返回的结果
        String body = readBody(request);
        System.out.println(body);
        JSONObject jsonObject0=JSON.parseObject(body);
        CompileRequest compileRequest = gson.fromJson(body, CompileRequest.class);
        CompileResponse compileResponse = new CompileResponse();
        if(compileRequest.getCode().equals("")){
            compileResponse.setOk(-1);
            compileResponse.setReason("提交失败，请输入代码");
        }
        else {
            ProblemDao problemDao = new ProblemDao();
            Problem problem = problemDao.selectOne(compileRequest.getId());
            ArrayList<String> env = new ArrayList<String>();
            env.add("LANG=en_US.UTF-8");
            env.add("LANGUAGE=en_US:en");
            env.add("LC_ALL=en_US.UTF-8");
            Producer p = new Producer();
            Run run = p.produceRun();
            Compile compile = p.produceCompile();
            Language_config language_config = p.produceLanguage_config(run, compile);

            String src = compileRequest.getCode();
            String test_case_id = problem.getTest_case_id();
            JudgeJson judgeJson = p.produceJudgeJson(language_config, src, test_case_id);
            String jsonCode = gson.toJson(judgeJson);

            Judger judger = new Judger();
            String result = judger.sendPostByString(jsonCode);
            JSONObject jsonObject = JSON.parseObject(result);
            if(result.contains("CompileError")){
                compileResponse.setOk(-1);
                compileResponse.setReason("CompileError");
            }
            else {
                JSONArray jsonObject6= (JSONArray) jsonObject.get("data");
                int count=0;
                for(int i=0;i<problem.getTest_case_number();i++){
                    JSONObject jsonObject9= (JSONObject) jsonObject6.get(i);
                    if(jsonObject9.get("result").equals(0)){
                        count++;
                    }
                }
                if(count==problem.getTest_case_number()){
                    compileResponse.setOk(1);
                    compileResponse.setReason("Accept");
                    System.out.println("000");
                    Cookie[] cookies = request.getCookies();
                    if(cookies != null && cookies.length > 0){
                        for (Cookie cookie : cookies){
                            if(cookie.getName().equals("username")){
                                username=cookie.getValue();
                            }
                        }
                    }
                    User_ProblemDao user_problemDao=new User_ProblemDao();
                    try {
                        int grade=(count/problem.getTest_case_number())*100;
                        System.out.println(grade);
                        user_problemDao.insert(username,String.valueOf(problem.getId()),grade);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    compileResponse.setOk(0);
                    compileResponse.setReason("共"+problem.getTest_case_number()+"样例，对了"+count+"组");
                }
            }

        }
        responseJson=gson.toJson(compileResponse);
        response.getWriter().write(responseJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private String readBody(HttpServletRequest req) {
        //body 的长度在 header 中的一个 Content-Length 字段中
        //contentLength 的单位就是字节
        int contentLength = req.getContentLength();
        byte[] buf = new byte[contentLength];
        try (InputStream is = req.getInputStream()) {
            is.read(buf, 0, contentLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buf);
    }
}
