package Recommender;

import SQL.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AverageProblemGrade {
    public double averageUserGrade(String problem_id)throws SQLException {
        double sum=0;
        double count=0;
        double aver=0;
        Connection connection = DButil.getConnection();
        String sql = "select * from user_problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if (resultSet.getString("problem_id").equals(problem_id)) {
                    count++;
                    sum+=resultSet.getInt("grade");
                }
            }
            if(count>0){
                aver=sum/count;
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }
        return aver;
    }
}
