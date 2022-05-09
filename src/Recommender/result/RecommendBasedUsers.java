package Recommender.result;

import Class.Problem.Problem;
import Class.Problem.ProblemDao;
import Recommender.data.ReadUsersData;

import java.util.HashMap;

public class RecommendBasedUsers {

    /**
     *
     * @param username
     * @return
     */
    public static String[] recommendResult(String username){
        String[] reResult=new String[50];
        ReadUsersData readUsersData=new ReadUsersData();
        HashMap<String,Integer> userC=new HashMap<String,Integer>();

        userC=readUsersData.computeUserCapability();;

        HashMap<String, HashMap<String, Integer>> allUserResult = new HashMap<String, HashMap<String, Integer>>();

        allUserResult=readUsersData.getAllUserResult();

        Integer c=userC.get(username);

       //System.out.println(c);
        Integer diff=100;
        for (String key : allUserResult.keySet()) {
//            System.out.println(key);
            if(key.equals(username)){
                continue;
            }
            else{
                Integer diff1=Math.abs(c-userC.get(key));
                if(diff1<diff){
                    diff=diff1;
                    HashMap<String, Integer> oneUser = allUserResult.get(key);
                    int j=0;
                    for (String key1 : oneUser.keySet()) {
                        reResult[j]=key1;
                        //System.out.println(key1);
                        j++;
                    }
                }
            }
        }
        return reResult;
    }


    public static void main(String[] args) {
        String[] re=new String[50];
        ProblemDao problemDao=new ProblemDao();

        re=recommendResult("ybHhHAlH1r");


        for(int i=0;i<5;i++){
            System.out.println(re[i]);
            System.out.println(problemDao.selectOne(Integer.valueOf(re[i])).getTest_case_id());
        }

    }
}
