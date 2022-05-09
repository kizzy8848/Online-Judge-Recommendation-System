package Recommender.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadUsersData {
    public String readFile() {
        String path = "E:\\Desktop\\SIT\\hnuOnlineJudge\\src\\Recommender\\data\\users.txt";
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
        return result.toString();
    }

    public static String[] readFileByLine() {
        String path = "E:\\Desktop\\SIT\\hnuOnlineJudge\\src\\Recommender\\data\\users.txt";
        File file = new File(path);

        String[] userArray = new String[2000];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

            String s = null;
            int i = 0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                userArray[i] = s;
//                System.out.println(s);
                i++;
            }
//            System.out.println(i);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userArray;
    }


    public static HashMap<String, HashMap<String, Integer>> getAllUserResult() {
        String[] p = readFileByLine();
//        System.out.println(p);
//        System.out.println(p.length);

        HashMap<String, HashMap<String, Integer>> allUserResult = new HashMap<String, HashMap<String, Integer>>();

        String username;

        for (int i = 0; i < p.length; i++) {

            String[] arr = p[i].split("\\s+");
            int j = arr.length;
            username = arr[0];

            HashMap<String, Integer> onePair = new HashMap<String, Integer>();

            for (int l = 1; l < j; l += 2) {
                onePair.put(arr[l], Integer.valueOf(arr[l + 1]));
            }
            allUserResult.put(username, onePair);
        }
        return allUserResult;
    }

    public static HashMap<String, Integer> computeUserCapability() {
        HashMap<String, Integer> userCapability = new HashMap<String, Integer>();
        ReadProblemsData readProblemData = new ReadProblemsData();
        double[] DC = readProblemData.computeDifficultyCoefficient();
        HashMap<String, HashMap<String, Integer>> allUserResult = getAllUserResult();
        double[] c = new double[1000];
        for (String key : allUserResult.keySet()) {
//            System.out.println(key);
            int i = 0;
            int all = 0;
            HashMap<String, Integer> oneUser = allUserResult.get(key);
            for (String key1 : oneUser.keySet()) {
//                System.out.println(Integer.valueOf(key1));

                all += (1 - DC[Integer.valueOf(key1) - 1002]) * oneUser.get(key1);
                i++;
            }
//            System.out.println("all:"+all);
            userCapability.put(key, all / i);

        }
        return userCapability;
    }

    public ArrayList<String> select200UsersRandom() {
        ArrayList<String> _200users = new ArrayList<String>();

        HashMap<String, HashMap<String, Integer>> allUserResult = new HashMap<String, HashMap<String, Integer>>();

        allUserResult = getAllUserResult();
        int i = 0;
        for (String key : allUserResult.keySet()) {
            if (i % 10 == 5) {
                _200users.add(key);
            }
            i++;
        }
        return _200users;
    }

    public static int[] selected200UsersProblemNumber() {
        int[] a = new int[200];

        HashMap<String, HashMap<String, Integer>> allUserResult = new HashMap<String, HashMap<String, Integer>>();

        allUserResult = getAllUserResult();
        int i = 0;
        int x=0;
        for (String key : allUserResult.keySet()) {
            if (i % 10 == 5) {
                HashMap<String, Integer> m = allUserResult.get(key);
                int j = 0;
                for (String key1 : m.keySet()) {
                    System.out.println(key1);
                    j++;
                }
                a[x] = j;
                x++;
            }
            i++;
        }

        return a;
    }

    public static int[] selected200UsersCapability() {
        int[] a = new int[200];

        HashMap<String, Integer> userCapability = new HashMap<String, Integer>();
        userCapability=computeUserCapability();

        int i = 0;
        int x=0;
        for (String key : userCapability.keySet()) {
            if (i % 10 == 5) {
               a[x]=userCapability.get(key);
               x++;
            }
            i++;
        }

        return a;
    }

    public static void main(String[] args) {

//        HashMap<String,Integer> userCapability=new HashMap<String, Integer>();
//        userCapability=computeUserCapability();
//
//        System.out.println(userCapability.get("Iqw3qM3TUi"));
//
//        for(String key:userCapability.keySet()){
//
//            if(userCapability.get(key)==0){
//                System.out.println(key);
//            }
////            System.out.println(userCapability.get(key));
//            }
//    }
        int[] a = new int[200];
        a=selected200UsersCapability();
        for (int i = 0; i < 200; i++) {
            System.out.print(a[i] + ",");

        }
        System.out.println();
        System.out.println(a.length);
    }
}
