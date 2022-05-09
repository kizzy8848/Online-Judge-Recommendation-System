package Class.Problem;



import Recommender.AverageUserGrade;
import SQL.DButil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDao {
    public List<Problem> selectAll(String username) throws SQLException {
        AverageUserGrade averageUserGrade=new AverageUserGrade();
        double averageusergrade = averageUserGrade.averageUserGrade("username");
        List<Problem> ret=new ArrayList<Problem>() ;
        Connection connection = DButil.getConnection();
        //2.拼装sql语句
        String sql = "select * from problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            //3.执行sql语句
            resultSet = statement.executeQuery();

            //4.遍历结果集
            while (resultSet.next()) {
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setDescription(resultSet.getString("description"));
                problem.setInput_description(resultSet.getString("input_description"));
                problem.setOutput_description(resultSet.getString("output_description"));
                problem.setSample_input(resultSet.getString("sample_input"));
                problem.setSample_output(resultSet.getString("sample_output"));
                problem.setHint(resultSet.getString("hint"));
                problem.setDifficulty(resultSet.getInt("difficulty"));
                problem.setTest_case_id(resultSet.getString("test_case_id"));
                problem.setTest_case_number(resultSet.getInt("test_case_number"));
                problem.setSrc(resultSet.getString("src"));
                ret.add(problem);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DButil.close(connection, statement, resultSet);
        }
        System.out.println(1);
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Problem selectOne(int id) {
        //1.建立连接
        Connection connection = DButil.getConnection();
        //2.拼装sql语句
        String sql = "select * from problem where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.处理结果集
            if (resultSet.next()) {
                Problem problem=new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setDescription(resultSet.getString("description"));
                problem.setInput_description(resultSet.getString("input_description"));
                problem.setOutput_description(resultSet.getString("output_description"));
                problem.setSample_input(resultSet.getString("sample_input"));
                problem.setSample_output(resultSet.getString("sample_output"));
                problem.setHint(resultSet.getString("hint"));
                problem.setDifficulty(resultSet.getInt("difficulty"));
                problem.setTest_case_id(resultSet.getString("test_case_id"));
                problem.setTest_case_number(resultSet.getInt("test_case_number"));
                problem.setSrc(resultSet.getString("src"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DButil.close(connection, statement, resultSet);
        }
        return null;
    }

    public int selectProblemDifficulty(int id) {
        //1.建立连接
        Connection connection = DButil.getConnection();
        //2.拼装sql语句
        String sql = "select * from problem where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            //3.执行sql语句
            resultSet = statement.executeQuery();
            //4.处理结果集
            if (resultSet.next()) {
                return resultSet.getInt("difficulty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.关闭连接
            DButil.close(connection, statement, resultSet);
        }
        return 0;
    }
}