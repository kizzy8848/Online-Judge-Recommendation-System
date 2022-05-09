package SQL;


import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DButil {
    private Connection con=null; // 数据库连接对象
    private  static final String URL = PropertyUtil.getValue("url");// 数据库连接地址
    private  static final String UserName = PropertyUtil.getValue("user");// 数据库的用户名
    private  static final String Password = PropertyUtil.getValue("password");// 数据库的密码
    private  static String DriverClass=PropertyUtil.getValue("driverClass"); //驱动名称
    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DButil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)(dataSource)).setURL(URL);
                    ((MysqlDataSource)(dataSource)).setUser(UserName);
                    ((MysqlDataSource)(dataSource)).setPassword(Password);
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection() {

        try {
            //内置了数据库连接池
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (result != null) { result.close(); }
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
