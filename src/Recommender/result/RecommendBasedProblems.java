package Recommender.result;

import Recommender.data.ReadProblemsData;
import Recommender.data.ReadUsersData;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

public class RecommendBasedProblems {


    /**
     *
     * @param username
     * @return
     */
    public static String[] recommendResult(String username) {


        ReadUsersData readUsersData=new ReadUsersData();
        HashMap<String, HashMap<String, Integer>> allUserResult = new HashMap<String, HashMap<String, Integer>>();
        allUserResult=readUsersData.getAllUserResult();



        String[] reResult=new String[50];
        ReadProblemsData readData = new ReadProblemsData();

        double[] DC = new double[49];

        DC = readData.computeDifficultyCoefficient();

        double miUserCap=0;
        for (String key : allUserResult.keySet()) {
//            System.out.println(key);
            if(key.equals(username)){

                HashMap<String, Integer> oneUser = allUserResult.get(key);
                int j=0;
                double totalScore=0;
                for (String key1 : oneUser.keySet()) {


                    double problemDC = DC[Integer.valueOf(key1) - 1002];

                    totalScore+=oneUser.get(key1)*problemDC;

                    j++;



                }


                miUserCap=totalScore/(j*100);

                BigDecimal B = new BigDecimal(miUserCap);

                miUserCap=B.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                System.out.println(totalScore);
//                System.out.println(j);
//                System.out.println(miUserCap);


                double[] difference=new double[49];
                double[] difference1=new double[49];
                for(int i=0;i<49;i++){
                    difference[i]=Math.abs(miUserCap-DC[i]);
                    difference1[i]=Math.abs(miUserCap-DC[i]);
                }


//                for(int i=0;i<49;i++){
//                    System.out.println(difference1[i]);
//                }
                Arrays.sort(difference);
                int u=0;
                for (int i=0;i<49;i++){
                    //System.out.println(difference[i]);
                    for(int jj=0;jj<49;jj++){
                        if(i>0){
                            if(difference[i]==difference[i-1]){
                                continue;
                            }
                            else if(difference[i]==difference1[jj]){
                                reResult[u]=String.valueOf(1002+jj);
                                u++;
                            }
                        }
                        if(i==0){
                            if(difference[i]==difference1[jj]){
                                reResult[u]=String.valueOf(1002+jj);
                                u++;
                            }
                        }

                    }
                }
                break;
            }

        }
//        for(int j=0;j<49;j++){
//            System.out.println(reResult[j]);
//        }

//        System.out.println(reResult.length);



        return reResult;

    }

    public static void main(String[] args) {
        recommendResult("ybHhHAlH1r");
    }


}
