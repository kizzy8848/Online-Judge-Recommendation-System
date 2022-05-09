package Judger.JudgeSources;


import java.util.ArrayList;

public class Run {
    private String command= "{exe_path}";
    private String seccomp_rule="c_cpp";
    private ArrayList<String> env;

    public Run(ArrayList<String> env) {
        this.env = env;
    }

    public String getCommand() {
        return command;
    }

    public String getSeccomp_rule() {
        return seccomp_rule;
    }

    public ArrayList<String> getEnv() {
        return env;
    }
}
