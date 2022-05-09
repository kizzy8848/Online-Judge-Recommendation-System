package test;

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

import Class.User_Problem.User_ProblemDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class test1{
    public static void main(String[] args) throws Exception{
//        ProblemDao problemDao=new ProblemDao();
//        Class.Problem problem=problemDao.selectOne(1);
//        System.out.println(problem.getDifficulty());
        String s ="{\"action\":\"add\",\"id\":\"1\",\"ordinal\":8,\"organUnitFullName\":\"testJSON\",\"parent\":\"0\",\"suborderNo\":\"58961\"}";
        JSONObject jsonObject= JSON.parseObject(s);
        System.out.println(jsonObject);
        User_ProblemDao user_problemDao=new User_ProblemDao();
        user_problemDao.insert("kizzy","2",79);
//        "{\"id\":1,\"code":"#include <iostream>\nusing namespace std;\nint main(){\n  int a,b;\n  cin>>a>>b;\n  cout<<a+b;\n  return 0;\n}"}"
    }
}
