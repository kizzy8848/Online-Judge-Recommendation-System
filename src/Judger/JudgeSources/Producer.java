package Judger.JudgeSources;

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

import java.util.ArrayList;

public class Producer {
    public Run produceRun(){
        ArrayList<String> env=new ArrayList<>();
        env.add("LANG=en_US.UTF-8");
        env.add("LANGUAGE=en_US:en");
        env.add("LC_ALL=en_US.UTF-8");
        Run run=new Run(env);
        return run;
    }
    public Compile produceCompile(){
        Compile compile=new Compile();
        return compile;
    }
    public Language_config produceLanguage_config(Run run,Compile compile){
        Language_config language_config=new Language_config();
        language_config.setCompile(compile);
        language_config.setRun(run);
        return language_config;
    }
    public JudgeJson produceJudgeJson(Language_config language_config,String src,String test_case_id){
        JudgeJson judgeJson=new JudgeJson();
        judgeJson.setLanguage_config(language_config);
        judgeJson.setSrc(src);
        judgeJson.setTest_case_id(test_case_id);
        return judgeJson;
    }
}
