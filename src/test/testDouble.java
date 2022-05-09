package test;
import Judger.Judger;
import Recommender.AverageProblemGrade;
import Recommender.AverageUserGrade;

import java.io.IOException;
import java.sql.SQLException;

public class testDouble {
    public static void main(String[] args) throws IOException, SQLException {
//        AverageProblemGrade averageProblemGrade=new AverageProblemGrade();
//        System.out.println(averageProblemGrade.averageUserGrade("kizzy"));
        AverageUserGrade averageUserGrade=new AverageUserGrade();
        System.out.println(averageUserGrade.averageUserGrade("kizzy"));
    }
}
