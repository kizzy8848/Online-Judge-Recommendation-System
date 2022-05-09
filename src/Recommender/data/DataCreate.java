package Recommender.data;



import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class DataCreate {
    /**
     * problems
     * 数据结构 list
     *
     */


    public void problemDataCreate(){
        Random r1 = new Random(2);
        Random r2 = new Random(20);
        WriteTxt writeTxt=new WriteTxt("E:\\Desktop\\SIT\\hnuOnlineJudge\\src\\Recommender\\data\\problems.txt");
        for(int i=1002;i<=1050;i++){
            writeTxt.writeToTXT(String.valueOf(i)+" ");
            int ran1 = r1.nextInt(1000);
            for(int j=0;j<ran1;j++){
                int ran2= r2.nextInt(20);{
                   writeTxt.writeToTXT(String.valueOf(ran2*5)+" ");
                }
            }
            writeTxt.writeToTXT("\n");
        }
    }

    /**
     *users
     * 数据结构 list
     *
     */

    public void userDateCreate(){
        Random r1 = new Random(28);
        Random r2 = new Random(21);
        Random r3 = new Random(5);
        WriteTxt writeTxt=new WriteTxt("E:\\Desktop\\SIT\\hnuOnlineJudge\\src\\Recommender\\data\\users.txt");
        for(int i=0;i<1000;i++){
            String username= RandomStringUtils.randomAlphanumeric(10);
            writeTxt.writeToTXT(username+" ");
            int ran1 = r1.nextInt(50);
            if(ran1!=0) {
                for (int j = 0; j < ran1; j++) {
                    int ran2 = r2.nextInt(48);
                    int ran3 = r3.nextInt(20);
                    writeTxt.writeToTXT(String.valueOf(ran2 + 1002) + " ");
                    writeTxt.writeToTXT(String.valueOf(5 * ran3) + " ");
                }
            }else{
                ran1=r1.nextInt(50);
                for (int j = 0; j < ran1; j++) {
                    int ran2 = r2.nextInt(48);
                    int ran3 = r3.nextInt(20);
                    writeTxt.writeToTXT(String.valueOf(ran2 + 1002) + " ");
                    writeTxt.writeToTXT(String.valueOf(5 * ran3) + " ");
                }
            }
            writeTxt.writeToTXT("\n");
        }



    }



    public static void main(String[] args)
    {
        Random r = new Random(4);
        for(int i=0 ; i<5 ;  i++)
        {
            int ran1 = r.nextInt(20);
            System.out.println(ran1);
        }
//        problemDataCreate();
        String filename= RandomStringUtils.randomAlphanumeric(10);
        System.out.println(filename);
    }
}

