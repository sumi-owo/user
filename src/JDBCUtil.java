package user;

import java.sql.*;

public class JDBCUtil {
    public static String url = "jdbc:mysql://101.43.248.189:3306/userlib";
    public static String user = "root0";
    public static String passwd = "AHDNbkwXBefAKaA2";

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static Connection getConnection() throws Throwable {
        loadDriver();
        return DriverManager.getConnection(url, user, passwd);
    }

    public static void release(Statement stmt, ResultSet rs, Connection conn) {
        //6释放资源
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
