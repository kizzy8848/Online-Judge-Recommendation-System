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

/**
 * 用于json
 */
public class Compile {
    private String src_name="main.cpp";
    private String exe_name="main";
    private int max_cpu_time=3000;
    private int max_real_time=5000;
    private long max_memory=134217728;
    private String compile_command="/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c++11 {src_path} -lm -o {exe_path}";

    public String getSrc_name() {
        return src_name;
    }

    public String getExe_name() {
        return exe_name;
    }

    public int getMax_cpu_time() {
        return max_cpu_time;
    }

    public int getMax_real_time() {
        return max_real_time;
    }

    public long getMax_memory() {
        return max_memory;
    }

    public String getCompile_command() {
        return compile_command;
    }
}
