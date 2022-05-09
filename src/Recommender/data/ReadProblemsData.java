package Recommender.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class ReadProblemsData {

    public String readFile()
    {
        String path = "E:\\Desktop\\毕设\\code\\milkoj\\study\\src\\test\\java\\hnu\\kizzy\\fileManipulationTest\\problems.txt";
        File file = new File(path);
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result.toString();

    }

    public String[] readFileByLine(){
        String path = "E:\\Desktop\\毕设\\code\\milkoj\\study\\src\\test\\java\\hnu\\kizzy\\fileManipulationTest\\problems.txt";
        File file = new File(path);
        String[] problemArray=new String[50];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

            String s = null;
            int i=0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                problemArray[i]=s;
//                System.out.println(s);
                i++;
            }
//            System.out.println(i);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return problemArray;
    }

    public long[][] getTotalScore(){
        String[] p=readFileByLine();
        long[][] result=new long[49][3];
        for(int i=0;i<49;i++){
            String [] arr = p[i].split("\\s+");
            int j=arr.length;
            long score=0;
            long max=0;
            for(int l=1;l<j;l++){
                score+=Integer.valueOf(arr[l]);
                if(max<Integer.valueOf(arr[l])){
                    max=Integer.valueOf(arr[l]);
                }
            }
            result[i][0]=score;
            result[i][1]=j-1;
            result[i][2]=max;
        }
        return result;
    }

    public long regularization(double d, long max){
        long in;
        in = (long) (d + max);
        in= max / in;
        return in;
    }

    public double[] computeDifficultyCoefficient(){
        long[][] re=new long[49][3];
        re=getTotalScore();
        double[] DC=new double[49];
        double e=Math.E;

        double b;

        for(int i=0;i<49;i++){
            double middle=0;
            middle=100*re[i][1];
            middle/=re[i][0];
            middle-=1;
            b=log(e,middle);
            //DC[i]=regularization(b,re[i][2]);

            BigDecimal B = new BigDecimal(b);
            b=B.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            DC[i]=Math.abs(b);
            //DC[i]=regularization(b,re[i][2]);
        }

        return DC;


    }

    public static double log(double basement, double n){
        return Math.log(n) / Math.log(basement);
    }

}

