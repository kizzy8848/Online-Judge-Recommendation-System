package Class.Problem;


/**
 * 题目类
 */
public class Problem {
    private int id;//primary key
    private String title;
    private String description;
    private String input_description;
    private String output_description;
    private String sample_input;
    private String sample_output;
    private String hint;
    private String tag[];
    private int difficulty;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    private String src;

    public String[] getTag() {
        return tag;
    }

    public int getTest_case_number() {
        return test_case_number;
    }

    private int test_case_number;

    public void setTest_case_number(int test_case_number) {
        this.test_case_number = test_case_number;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public void setTest_case_id(String test_case_id) {
        this.test_case_id = test_case_id;
    }

    private String test_case_id;


    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getInput_description(){return input_description;}
    public String getOutput_description(){return output_description;}
    public String getSample_input(){return sample_input;}
    public String getSample_output(){return sample_output;}
    public String getHint(){return hint;}
    public int getDifficulty(){return difficulty;}
    public String getTest_case_id(){return test_case_id;}

    public void setId(int id){this.id=id;}
    public void setTitle(String title){this.title=title;}
    public void setDescription(String description){this.description=description;}
    public void setInput_description(String input_description){this.input_description=input_description;}
    public void setOutput_description(String output_description){this.output_description=output_description;}
    public void setSample_input(String sample_input){this.sample_input=sample_input;}
    public void setSample_output(String sample_output){this.sample_output=sample_output;}
    public void setHint(String hint){this.hint=hint;}
    public void setTag(){}
    public void setDifficulty(int difficulty){this.difficulty=difficulty;}
}