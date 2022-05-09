package Recommender.evaluate;

import Recommender.data.ReadProblemsData;
import Recommender.data.ReadUsersData;
import Recommender.result.RecommendBasedUsers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MatchingcoefficientBasedUsers {


    public static double[] computeMatchingCoefficient(){
        double[] MC=new double[200];

        ReadUsersData readUsersData=new ReadUsersData();
        RecommendBasedUsers recommendBasedUsers=new RecommendBasedUsers();
        ReadProblemsData readData=new ReadProblemsData();

        HashMap<String,Integer> userCapability=new HashMap<String, Integer>();
        userCapability=readUsersData.computeUserCapability();

        double[] DC=new double[49];
        DC=readData.computeDifficultyCoefficient();

        Random r = new Random(5);

        ArrayList<String> _200user=new ArrayList<>();
        _200user=readUsersData.select200UsersRandom();
        try{
            int n=0;
            for(String username:_200user){
                double averageScore=0;
                String[] reResult=new String[50];
                reResult=recommendBasedUsers.recommendResult(username);
                for(int i=0;i<5;i++){
                    int ran = r.nextInt(20);
                    averageScore+=DC[Integer.valueOf(reResult[i])-1002]*ran*5;
                }

                int userC=50;
                for(String key:userCapability.keySet()){
                    if(username.equals(key)){
                        userC=userCapability.get(key);
                        break;
                    }
                }

//                    System.out.println(username);
//                    System.out.println(userC);
//                    System.out.println("a" + averageScore);


                double mid=averageScore/userC;
                BigDecimal B = new BigDecimal(mid);
                mid=B.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                MC[n]=mid;

                n++;

            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return  MC;
    }

    public static double computeAverageMatchingCoefficient(){
        double[] MC=new double[200];
        MC=computeMatchingCoefficient();
        double averageMC;
        double totalMC=0;
        for(int i=0;i<200;i++){
            totalMC+=MC[i];
        }
        averageMC=totalMC/200;
        return  averageMC;

    }


    public static void main(String[] args) {
        RecommendBasedUsers recommendBasedUsers=new RecommendBasedUsers();


        double[] MC=new double[200];
        MC=computeMatchingCoefficient();


        for(int i=0;i<200;i++){
            System.out.print(MC[i]+",");

        }

        System.out.println(computeAverageMatchingCoefficient());

    }
}
