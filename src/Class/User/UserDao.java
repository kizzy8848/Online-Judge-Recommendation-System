
package Class.User;

import SQL.DButil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDao {
    public UserDao() {
    }

    public int verification(String username, String passward) throws SQLException {
        Connection connection = DButil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where username = '" + username + "' ;";

        byte var7;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                byte var13 = -1;
                return var13;
            }

            if (resultSet.getString(3).equals(passward)) {
                var7 = 1;
                return var7;
            }

            var7 = 0;
        } catch (SQLException var11) {
            var11.printStackTrace();
            return 2;
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        return var7;
    }

    public List<User> selectALL() {
        List<User> ret = new ArrayList();
        Connection connection = DButil.getConnection();
        String sql = "select * from users";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                ret.add(user);
            }

            ArrayList var12 = (ArrayList) ret;
            return var12;
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        System.out.println(1);
        return null;
    }

    public int register(String username, String password) throws SQLException {
        List<User> users = this.selectALL();
        int Id = 0;
        Iterator var5 = users.iterator();

        User user;
        do {
            if (!var5.hasNext()) {
                ++Id;
                Connection connection = DButil.getConnection();
                String sql = "insert into users values(" + Id + ",'" + username + "','" + password + "');";
                PreparedStatement statement = null;

                try {
                    statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                } catch (SQLException var12) {
                    var12.printStackTrace();
                } finally {
                    connection.close();
                }

                return 1;
            }

            user = (User)var5.next();
            if (user.getId() > Id) {
                Id = user.getId();
            }
        } while(!user.getUsername().equals(username));

        return 0;
    }
}
