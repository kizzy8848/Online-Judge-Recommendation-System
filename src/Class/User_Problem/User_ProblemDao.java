package Class.User_Problem;

import SQL.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class User_ProblemDao {

    public void insert(String username, String problem_id,int grade) throws Exception {

        Connection connection = DButil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectSQL= "select * from user_problem";

        try{
            statement = connection.prepareStatement(selectSQL);
            resultSet = statement.executeQuery();
            int flag=0;
            while(resultSet.next()) {
//                System.out.println(resultSet.getString("username"));
                if (resultSet.getString("username").equals(username) && resultSet.getString("problem_id").equals(problem_id)) {
                   if(grade<=resultSet.getInt("grade")){
//                       System.out.println(resultSet.getInt("grade"));

                       System.out.println(3);
                   }
                   else{
                       System.out.println(resultSet.getInt("grade"));
                       update(username, problem_id,grade);
                       System.out.println(2);
                   }
                   flag=1;
                   break;

                }
            }
            if(flag==1){
                System.out.println(4);
            }else {
                insert1(username,problem_id,grade);
                System.out.println(90);
            }
        }catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            connection.close();
        }


    }
    public void update(String username, String problem_id,int grade)throws Exception{
        Connection connection = DButil.getConnection();
        String updateSQl="update user_problem set grade='"+grade+"' where username='"+ username + "' and problem_id= '"+problem_id+"';";
        try {
            PreparedStatement ps = connection.prepareStatement(updateSQl);
            ps.executeUpdate();

        }catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            connection.close();
        }
    }
    public void insert1(String username, String problem_id,int grade)throws Exception{
        Connection connection = DButil.getConnection();
        String sql = "insert into user_problem values('" + username + "','" + problem_id + "', '"+ grade+ "');";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("success");

        }catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public boolean select(String username, String problem_id) throws Exception {
        Connection connection = DButil.getConnection();
        String sql = "select * from user_problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if (resultSet.getString("username").equals(username) && resultSet.getString("problem_id").equals(problem_id)) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        return flag;
    }

    public HashMap<String, ArrayList<String>> selectOne(String username) throws Exception {
        ArrayList<String> resultList = new ArrayList();
        Connection connection = DButil.getConnection();
        String sql = "select * from user_problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    resultList.add(resultSet.getString("problem_id"));
                }
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        HashMap<String, ArrayList<String>> result = new HashMap();
        result.put(username, resultList);
        return result;
    }

    public ArrayList<String> selectOneReturnArrayList(String username) throws Exception {
        ArrayList<String> resultList = new ArrayList();
        Connection connection = DButil.getConnection();
        String sql = "select * from user_problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    resultList.add(resultSet.getString("problem_id"));
                }
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        return resultList;
    }

    public ArrayList<HashMap<String, ArrayList<String>>> selectAll() throws Exception {
        new ArrayList();
        new HashMap();
        ArrayList<HashMap<String, ArrayList<String>>> resultArrayList = new ArrayList();
        ArrayList<String> username = this.selectAllUsername();
        Iterator var5 = username.iterator();

        while(var5.hasNext()) {
            String s = (String)var5.next();
            resultArrayList.add(this.selectOne(s));
        }

        new HashMap();
        return resultArrayList;
    }

    public ArrayList<String> selectAllUsername() throws Exception {
        ArrayList<String> allUsername = new ArrayList();
        Connection connection = DButil.getConnection();
        String sql = "select * from user_problem";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                if (allUsername.indexOf(resultSet.getString("username")) == -1) {
                    allUsername.add(resultSet.getString("username"));
                }
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        return allUsername;
    }

    public static void main(String[] args) throws Exception {
        User_ProblemDao user_problemDao = new User_ProblemDao();
        new ArrayList();
        ArrayList<String> arrayList = (ArrayList)user_problemDao.selectOne("kizzy").get("kizzy");
        Collection<ArrayList<String>> s1 = user_problemDao.selectOne("kizzy").values();
        Set<String> stringConnection = user_problemDao.selectOne("kizzy").keySet();
        Iterator iter = stringConnection.iterator();

        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

        Set<Entry<String, ArrayList<String>>> set1 = user_problemDao.selectOne("kizzy").entrySet();
        Iterator var7 = set1.iterator();

        while(var7.hasNext()) {
            Entry<String, ArrayList<String>> entry = (Entry)var7.next();
            System.out.println((String)entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println(user_problemDao.selectOne("kizzy"));
        System.out.println(arrayList);
        var7 = arrayList.iterator();

        while(var7.hasNext()) {
            String s = (String)var7.next();
            System.out.println(s);
        }

        System.out.println(user_problemDao.selectAllUsername());
        System.out.println(user_problemDao.selectAll());
        ArrayList<String> t = (ArrayList)user_problemDao.selectOne("kizzy").get("kizzy");
        Collections.sort(t);
        Iterator var12 = t.iterator();

        while(var12.hasNext()) {
            String i = (String)var12.next();
            System.out.println(i);
        }

    }
}
