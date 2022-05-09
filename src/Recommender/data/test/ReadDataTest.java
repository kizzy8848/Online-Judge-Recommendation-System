package Recommender.data.test;

import Recommender.data.ReadProblemsData;

public class ReadDataTest {
    public static void main(String[] args) {
        ReadProblemsData readData=new ReadProblemsData();

//        String [][] result=new String[50][101];
////        System.out.println(readData.readFile());
//        String[] p=(readData.readFileByLine());
//        System.out.println(p.length);
//        for(int i=0;i<49;i++){
//            String [] arr = p[i].split("\\s+");
//            result[i]=arr;
//            for(String a:arr){
//                System.out.println(a);
//            }
//        }

//        long[][] re=new long[49][2];
//        re=readData.getTotalScore();
//        for(int i=0;i<49;i++){
//            System.out.println(re[i][0]);
//            System.out.println(re[i][1]);
//        }

        double[] DC=new double[49];
        DC=readData.computeDifficultyCoefficient();
        for(int i=0;i<49;i++){
            System.out.println(DC[i]);
        }
    }
}
