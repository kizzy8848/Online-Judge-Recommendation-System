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

public class JudgeJson {
    private String src;
    private Language_config language_config;
    private int max_cpu_time =1000;
    private long max_memory=134217728;
    private String test_case_id;
    private boolean output=true;

    public String getSrc() {
        return src;
    }

    public Language_config getLanguage_config() {
        return language_config;
    }

    public int getMax_cpu_time() {
        return max_cpu_time;
    }

    public long getMax_memory() {
        return max_memory;
    }

    public String getTest_case_id() {
        return test_case_id;
    }

    public boolean isOutput() {
        return output;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setLanguage_config(Language_config language_config) {
        this.language_config = language_config;
    }

    public void setMax_cpu_time(int max_cpu_time) {
        this.max_cpu_time = max_cpu_time;
    }

    public void setMax_memory(long max_memory) {
        this.max_memory = max_memory;
    }

    public void setTest_case_id(String test_case_id) {
        this.test_case_id = test_case_id;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }
}
